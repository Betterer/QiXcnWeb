package com.qixcnweb.qixian.service;

import com.qixcnweb.qixian.constant.Constant;
import com.qixcnweb.qixian.dao.UserDao;
import com.qixcnweb.qixian.domain.User;
import com.qixcnweb.qixian.utils.CommonUtils;
import com.qixcnweb.qixian.utils.FileUploadUtils;
import com.qixcnweb.qixian.utils.MD5Utils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by dingxiaochi on 2018/3/17.
 */
@Service
public class UserService {

    @Resource
    private FileUploadUtils fileUploadUtils;

    @Resource
    private UserDao userDao;

    /**
     * 保存用户
     * @param user
     */
    public void saveUser(User user){
        userDao.save(user);
    }

    /**
     * 用户注册
     * @param user
     */
    public void userRegister(User user){
        //将用户密码MD5加密
        String password = user.getPassword();
        String md5Password = MD5Utils.encode(password);
        user.setPassword(md5Password);
        //用户状态默认为0:未认证
        user.setStatus(Constant.USER_STATUS_DELETE);
        //保存用户
        userDao.save(user);
    }

    /**
     * 根据用户账号查询用户
     * @param name
     * @return
     */
    public User findUserByName(String name) {
        User user = userDao.findUserByName(name);
        return user;
    }

    /**
     * 根据电话号码查询用户
     * @param phone
     * @return
     */
    public User findUserByPhone(String phone) {
        User user = userDao.findUserByPhone(phone);
        return user;
    }

    /**
     * 根据点在邮箱查询用户
     * @param email
     * @return
     */
    public User findUserByEmail(String email) {
        User user = userDao.findUserByEmail(email);
        return user;
    }

    /**
     * 更新用户头像
     * @param file    图片文件
     * @param fileSuffix  图片后缀
     * @param user    当前登录用户
     */
    public String updateUserHead(File file,String fileSuffix, User user) throws IOException {
        //将文件重命名
        String imgName = fileUploadUtils.createImgName();
        String fileName = Constant.USER_HEAD_IMAGE+imgName+"."+fileSuffix;
        //将文件上传到阿里云OSS
        fileUploadUtils.upload2OSS(fileName,file);
        //将文件名更行到数据库
        user.setImage(fileName);
        //将临时文件删除
        file.delete();
        userDao.save(user);
        return fileName;
    }
}
