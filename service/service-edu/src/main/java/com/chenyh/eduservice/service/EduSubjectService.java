package com.chenyh.eduservice.service;

import com.chenyh.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyh.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file, EduSubjectService subjectService);

    List<OneSubject> getAllOneTwoSubject();
}
