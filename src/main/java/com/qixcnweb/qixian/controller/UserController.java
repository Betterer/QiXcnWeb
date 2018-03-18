package com.qixcnweb.qixian.controller;

import com.qixcnweb.qixian.domain.User;
import com.qixcnweb.qixian.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.org.mozilla.javascript.internal.json.JsonParser;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * Created by dingxiaochi on 2018/3/18.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    /**
     * 通过用户账号查找用户
     * @param name
     * @return
     */
    @GetMapping("/findUserByName")
    @ResponseBody
    public String findUserByName(String name){
        User user = userService.findUserByName(name);
        if(user!=null){
            return "false";
        }else{
            return "true";
        }
    }

    /**
     * 通过电话号码查询用户
     * @param phone
     * @return
     */
    @GetMapping("/findUserByPhone")
    @ResponseBody
    public String findUserByPhone(String phone){
        User user = userService.findUserByPhone(phone);
        if(user!=null){
            return "false";
        }else{
            return "true";
        }
    }

    /**
     * 通过电子邮箱查询用户
     * @param email
     * @return
     */
    @GetMapping("/findUserByEmail")
    @ResponseBody
    public String findUserByEmail(String email){
        User user = userService.findUserByEmail(email);
        if(user!=null){
            return "false";
        }else{
            return "true";
        }
    }
}
