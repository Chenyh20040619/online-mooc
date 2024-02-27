package com.chenyh.bulletchat.entity;

import lombok.Data;

@Data
public class BulletChatRequest {
    private Integer timestamp;
    private String content;
}
