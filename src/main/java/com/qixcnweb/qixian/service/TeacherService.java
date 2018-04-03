package com.qixcnweb.qixian.service;

import com.qixcnweb.qixian.constant.Constant;
import com.qixcnweb.qixian.dao.TeacherDao;
import com.qixcnweb.qixian.domain.Teacher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Created by dingxiaochi on 2018/4/3.
 */

@Service
public class TeacherService {

    @Resource
    private TeacherDao teacherDao;

    /**
     * 保存/更新教师信息
     * @param teacher
     */
    public void saveTeacher(Teacher teacher){
        teacherDao.save(teacher);
    }

    /**
     * 根据学校ID查询教师
     * @param schoolId
     * @return
     */
    public List<Teacher> findTeacherBySchoolId(Integer schoolId){
        List<Teacher> teacherList = teacherDao.findTeacherBySchoolId(schoolId);
        return teacherList;
    }

    /**
     * 根据学校ID查询教师,状态正常的
     * @param schoolId
     * @return
     */
    public List<Teacher> findTeacherBySchoolIdAndStatus(Integer schoolId, Integer status){
        List<Teacher> teacherList = teacherDao.findTeacherBySchoolIdAndStatus(schoolId,status);
        return teacherList;
    }

    /**
     * 根据教师ID查询教师
     * @param teacherId
     * @return
     */
    public Teacher findTeacherById(Integer teacherId) {
        Teacher teacher = teacherDao.findTeacherById(teacherId);
        return teacher;
    }
}
