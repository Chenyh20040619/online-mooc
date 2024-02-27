package com.chenyh.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenyh.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyh.eduservice.entity.frontvo.CourseFrontVo;
import com.chenyh.eduservice.entity.frontvo.CourseWebVo;
import com.chenyh.eduservice.entity.vo.CourseInfoVo;
import com.chenyh.eduservice.entity.vo.CoursePublishVo;

import java.util.Map;

public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo publishCourseInfo(String id);

    void removeCourse(String courseId);

    Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

    CourseWebVo getBaseCourseInfo(String courseId);

//    void removeCourse(String courseId);
}
