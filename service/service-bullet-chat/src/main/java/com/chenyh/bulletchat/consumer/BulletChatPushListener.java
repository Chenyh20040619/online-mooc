package com.chenyh.bulletchat.consumer;


import com.chenyh.bulletchat.entity.BulletChat;
import com.chenyh.bulletchat.service.BulletChatService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;


@RabbitListener(queues = "#{bulletPublishQueue.name}") //使用spel表达式来解决注解只能使用常量的问题#{}里面放的是一个bean.属性
@Component
@Slf4j
public class BulletChatPushListener {

    @Autowired
    BulletChatService bulletChatService;

    @RabbitHandler(isDefault = true)
    public void saveBulletChat(BulletChat bulletChat, Channel channel, Message message) {

        log.info("收到弹幕推送消息,弹幕id" + bulletChat.getId());
        bulletChatService.pushBullet(bulletChat);
        //手动应答
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
