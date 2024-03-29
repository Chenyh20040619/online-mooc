package com.chenyh.bulletchat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyh.bulletchat.entity.BulletChat;
import com.chenyh.bulletchat.mapper.BulletChatMapper;
import com.chenyh.bulletchat.service.BulletChatService;
import com.chenyh.bulletchat.websocket.WebSocketMap;
import com.chenyh.bulletchat.websocket.WebSocketService;
import com.chenyh.commonutils.JsonUtil;
import com.chenyh.commonutils.RedisUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class BulletChatServiceImpl extends ServiceImpl<BulletChatMapper, BulletChat> implements BulletChatService {
    private static final String VIDEO_Bullet_CHAT_List = "VideoBulletChatList";
    private static final String EXPIRE = "";
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void saveBullet(BulletChat bulletChat) {
        //保存到数据库
        baseMapper.insert(bulletChat);
        //保存到redis
        String videoBulletKey = getVideoBulletKey(bulletChat.getVideoId());
        Object rawBulletChatList = redisTemplate.opsForValue().get(videoBulletKey);
        List<BulletChat> bulletChatList;
        if (rawBulletChatList == null) {
            //redis没有,考虑一下是过期了,还是这是第一条弹幕
            LambdaQueryWrapper<BulletChat> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(BulletChat::getVideoId, bulletChat.getVideoId());
            bulletChatList = Optional.of(baseMapper.selectList(wrapper)).orElseGet(ArrayList::new);
        } else {
            //redis有
            bulletChatList = (List<BulletChat>) rawBulletChatList;
            bulletChatList.add(bulletChat);
        }
        //更新到redis,同时刷新该视频弹幕缓存的过期时间
        redisTemplate.opsForValue().set(videoBulletKey, bulletChatList, 10, TimeUnit.SECONDS);

    }

    private static String getVideoBulletKey(String videoId) {
        return RedisUtil.buildKey(VIDEO_Bullet_CHAT_List, videoId);
    }

    /**
     * 通过WebSocket向所有正在观看视频的用户发送弹幕
     *
     * @param bulletChat
     */
    @Override
    public void pushBullet(BulletChat bulletChat) {
        //optional避免空指针
        Optional.ofNullable(WebSocketMap.getAllByVideoId(bulletChat.getVideoId()))
                .ifPresent((map) -> {
                    for (WebSocketService webSocketService : map.values()) {
                        String bulletChatMessage = JsonUtil.toJsonString(bulletChat);
                        webSocketService.sendMessage(bulletChatMessage);
                    }
                });
    }

    @Override
    public List<BulletChat> getBulletsByVideoId(String videoId) {
        //从redis获取
        Object rawBulletChatList = redisTemplate.opsForValue().get(getVideoBulletKey(videoId));
        List<BulletChat> bulletChatList;
        if (rawBulletChatList == null) {
            //加锁读数据库,避免缓存穿透攻击
            synchronized (this) {
                //如果缺省,先查数据库,再加入缓存,然后返回
                LambdaQueryWrapper<BulletChat> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(BulletChat::getVideoId, videoId);
                bulletChatList = baseMapper.selectList(wrapper);
                redisTemplate.opsForValue().set(getVideoBulletKey(videoId), bulletChatList);
            }
        } else {
            bulletChatList = (List<BulletChat>) rawBulletChatList;
        }
        return bulletChatList;
    }
}
