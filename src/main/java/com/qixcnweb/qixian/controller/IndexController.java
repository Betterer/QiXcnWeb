package com.qixcnweb.qixian.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dingxiaochi on 2018/3/17.
 */
@Controller
public class IndexController {


    /**
     * 跳转到index页面
     * @return
     */
    @GetMapping("/index")
    public String index(){
        return "index";
    }

    /**
     * 跳转到登录页面
     * @return
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * 跳转到注册页面
     * @return
     */
    @GetMapping("/register")
    public String register(){
        return "register";
    }
}
