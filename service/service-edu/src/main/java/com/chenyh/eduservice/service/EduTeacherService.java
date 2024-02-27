package com.chenyh.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenyh.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;


public interface EduTeacherService extends IService<EduTeacher> {

    Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageTeacher);
}
