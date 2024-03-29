package com.chenyh.bulletchat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.chenle"}) //指定扫描位置
@EnableDiscoveryClient
@MapperScan("com.chenle.bulletchat.dao")
public class BulletChatApplication {
    public static void main(String[] args) {
        SpringApplication.run(BulletChatApplication.class, args);
    }
}

