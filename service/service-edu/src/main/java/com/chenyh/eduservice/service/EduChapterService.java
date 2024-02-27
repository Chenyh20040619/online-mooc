package com.chenyh.eduservice.service;

import com.chenyh.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyh.eduservice.entity.chapter.ChapterVo;

import java.util.List;


public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean deleteChapter(String chapterId);

    void removeChapterByCourseId(String courseId);
}
