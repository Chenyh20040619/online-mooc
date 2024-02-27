package com.chenyh.eduservice.service;

import com.chenyh.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

public interface EduVideoService extends IService<EduVideo> {

    void removeVideoByCourseId(String courseId);
}
