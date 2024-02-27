package com.chenyh.eduservice.mapper;

import com.chenyh.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenyh.eduservice.entity.frontvo.CourseWebVo;
import com.chenyh.eduservice.entity.vo.CoursePublishVo;

public interface EduCourseMapper extends BaseMapper<EduCourse> {

    CoursePublishVo getPublishCourseInfo(String id);

    CourseWebVo getBaseCourseInfo(String courseId);
}
