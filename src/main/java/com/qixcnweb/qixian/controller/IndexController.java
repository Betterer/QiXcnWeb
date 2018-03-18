package com.qixcnweb.qixian.controller;

import com.qixcnweb.qixian.domain.User;
import com.qixcnweb.qixian.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by dingxiaochi on 2018/3/17.
 */
@Controller
public class IndexController {

    @Resource
    private UserService userService;


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

    /**
     * 用户注册
     * @return
     */
    @PostMapping("/user_register")
    public String userRegister(@Valid User user, BindingResult result){
        if(result.hasErrors()){
            List<ObjectError> allErrors = result.getAllErrors();
            int errorCount = result.getErrorCount();
            System.out.println(errorCount);
            for(ObjectError error : allErrors){
                System.out.println(error.getCode()+"---"+error.getArguments()+"---"+error.getDefaultMessage());
            }
        }
        userService.saveUser(user);
        return "redirect:/index";
    }
}
