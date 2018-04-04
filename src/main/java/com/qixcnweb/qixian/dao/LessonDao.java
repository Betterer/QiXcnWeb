package com.qixcnweb.qixian.dao;

import com.qixcnweb.qixian.domain.Lesson;
import com.qixcnweb.qixian.domain.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by dingxiaochi on 2018/4/3.
 */
public interface LessonDao extends CrudRepository<Lesson, Long> {

    /** ------------------------------------------------- 增 -------------------------------------------------------- **/

    /** ------------------------------------------------- 删 -------------------------------------------------------- **/

    /** ------------------------------------------------- 改 -------------------------------------------------------- **/

    /** ------------------------------------------------- 查 -------------------------------------------------------- **/

    //根据课程ID查询课程
    Lesson findLessonById(Integer teacherId);

    //根据学校ID查询课程
    List<Lesson> findLessonBySchoolId(Integer schoolId);

    //根据学校ID和课程状态查询课程
    List<Lesson> findLessonBySchoolIdAndStatus(Integer schoolId,Integer status);
}
