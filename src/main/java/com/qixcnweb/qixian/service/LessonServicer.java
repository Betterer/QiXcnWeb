package com.qixcnweb.qixian.service;

import com.qixcnweb.qixian.dao.LessonDao;
import com.qixcnweb.qixian.domain.Lesson;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dingxiaochi on 2018/4/3.
 */
@Service
public class LessonServicer {

    @Resource
    private LessonDao lessonDao;

    /**
     * 根据学校ID查询课程
     * @param schoolId
     * @return
     */
    public List<Lesson> findLessonBySchoolId(Integer schoolId) {
        List<Lesson> lessonList = lessonDao.findLessonBySchoolId(schoolId);
        return lessonList;
    }
}
