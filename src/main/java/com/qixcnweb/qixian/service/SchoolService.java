package com.qixcnweb.qixian.service;

import com.qixcnweb.qixian.constant.Constant;
import com.qixcnweb.qixian.dao.SchoolDao;
import com.qixcnweb.qixian.domain.School;
import com.qixcnweb.qixian.domain.User;
import com.qixcnweb.qixian.utils.CommonUtils;
import com.qixcnweb.qixian.utils.FileUploadUtils;
import com.qixcnweb.qixian.utils.ImageUtils;
import com.qixcnweb.qixian.utils.JsonUtils;
import org.apache.solr.common.util.Hash;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dingxiaochi on 2018/3/29.
 */
@Service
public class SchoolService {

    @Resource
    private CommonUtils commonUtils;

    @Resource
    private ImageUtils imageUtils;

    @Resource
    private JsonUtils jsonUtils;

    @Resource
    private FileUploadUtils fileUploadUtils;

    @Resource
    private SchoolDao schoolDao;


    /**
     * 分页查询school对象
     * @return
     * @param searchBy      //根据哪个字段查询
     * @param currentPage   //当前页数
     * @param condition     //查询条件
     */
    public Page<School> findSchoolByPage(String searchBy, Integer currentPage, String condition){
        Pageable pageable = new PageRequest(currentPage, 9, Sort.Direction.ASC, "id");
        Page<School> page = null;
        if(searchBy.equals("name") && (condition!=null && !"".equals(condition))){
            page = schoolDao.findSchoolByNameContaining(condition,pageable);
        }else if(searchBy.equals("telephone") && (condition!=null && !"".equals(condition))){
            page = schoolDao.findSchoolByTelephoneContaining(condition,pageable);
        }else if(searchBy.equals("email") && (condition!=null && !"".equals(condition))){
            page = schoolDao.findSchoolByEmailContaining(condition, pageable);
        }else{
            page = schoolDao.findAll(pageable);
        }
        return page;
    }

    /**
     * 上传学校图片到OSS服务器
     * @param file
     * @param direct  OSS服务器上的目录
     * @return
     */
    public String uploadImages(MultipartFile file,String direct){
        //将图片重命名
        String imgName = imageUtils.createImgName(file.getOriginalFilename());
        //上传图片
        String filename = direct+imgName;
        try {
            fileUploadUtils.upload2OSS(filename,file.getInputStream());
        } catch (IOException e) {
            return "upload error";
        }
        return filename;
    }

    /**
     * 保存学校信息
     * @param school
     */
    public void saveSchool(School school) {
        schoolDao.save(school);
    }

    /**
     * 根据用户ID查询学校信息
     * @param userId
     * @return
     */
    public School findSchoolByUserId(Integer userId){
        School school = schoolDao.findSchoolByUserId(userId);
        return school;
    }

    /**
     * 根据学校ID查询学校信息
     * @param schoolId
     * @return
     */
    public School findSchoolById(Integer schoolId) {
        School school = schoolDao.findSchoolById(schoolId);
        return school;
    }
}
