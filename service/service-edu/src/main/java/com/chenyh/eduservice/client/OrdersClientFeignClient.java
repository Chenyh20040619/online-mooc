package com.chenyh.eduservice.client;

import org.springframework.stereotype.Component;

@Component
public class OrdersClientFeignClient implements OrdersClient{
    @Override
    public boolean isBuyCourse(String courseId, String memberId) {
        return false;
    }
}
