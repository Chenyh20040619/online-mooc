package com.chenyh.educenter.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenyh.educenter.entity.UcenterMember;

public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    Integer countRegisterDay(String day);
}
