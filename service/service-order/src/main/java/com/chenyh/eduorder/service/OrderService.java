package com.chenyh.eduorder.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyh.eduorder.entity.Order;

public interface OrderService extends IService<Order> {
    String createOrders(String courseId, String memberIdByJwtToken);

}
