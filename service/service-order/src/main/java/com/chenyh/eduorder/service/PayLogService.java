package com.chenyh.eduorder.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyh.eduorder.entity.PayLog;

import java.util.Map;

public interface PayLogService extends IService<PayLog> {
    Map createNatvie(String orderNo);

    Map<String, String> queryPayStatus(String orderNo);

    void updateOrdersStatus(Map<String, String> map);
}
