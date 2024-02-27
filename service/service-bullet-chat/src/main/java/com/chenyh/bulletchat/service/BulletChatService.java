package com.chenyh.bulletchat.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyh.bulletchat.entity.BulletChat;

import java.util.List;

public interface BulletChatService extends IService<BulletChat> {
    /**
     * 新增弹幕
     */
    void saveBullet(BulletChat bulletChat);

    /**
     * 推送弹幕
     */
    void pushBullet(BulletChat bulletChat);

    /**
     * 获取弹幕
     *
     * @return
     */
    List<BulletChat> getBulletsByVideoId(String videoId);
}
