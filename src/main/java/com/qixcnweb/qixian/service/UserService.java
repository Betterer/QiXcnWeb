package com.qixcnweb.qixian.service;

import com.qixcnweb.qixian.dao.UserDao;
import com.qixcnweb.qixian.domain.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by dingxiaochi on 2018/3/17.
 */
@Service
public class UserService {

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
}
