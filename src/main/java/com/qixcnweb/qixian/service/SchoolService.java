package com.qixcnweb.qixian.service;

import com.qixcnweb.qixian.constant.Constant;
import com.qixcnweb.qixian.dao.SchoolDao;
import com.qixcnweb.qixian.domain.School;
import com.qixcnweb.qixian.utils.CommonUtils;
import com.qixcnweb.qixian.utils.FileUploadUtils;
import com.qixcnweb.qixian.utils.ImageUtils;
import com.qixcnweb.qixian.utils.JsonUtils;
import org.apache.solr.common.util.Hash;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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
}
