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

    //根据用户ID查询教师
    Lesson findLessonById(Integer teacherId);

    //根据学校ID查询教师
    List<Lesson> findLessonBySchoolId(Integer schoolId);
}
