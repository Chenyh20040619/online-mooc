package com.chenyh.educenter.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyh.educenter.entity.UcenterMember;
import com.chenyh.educenter.entity.vo.RegisterVo;

public interface UcenterMemberService extends IService<UcenterMember> {

    //登录的方法
    String login(UcenterMember member);

    //注册的方法
    void register(RegisterVo registerVo);

    UcenterMember getOpenIdMember(String openid);

    Integer countRegisterDay(String day);

    boolean send(String code, String phone);
}
