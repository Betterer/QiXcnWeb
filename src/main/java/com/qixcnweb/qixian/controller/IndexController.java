package com.qixcnweb.qixian.controller;

import com.qixcnweb.qixian.domain.User;
import com.qixcnweb.qixian.service.UserService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by dingxiaochi on 2018/3/17.
 */
@Controller
public class IndexController {

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private UserService userService;

    /**
     * 跳转到index页面
     * @return
     */
    @GetMapping("/")
    public String index1(){
        return "redirect:/index";
    }

    /**
     * 跳转到index页面
     * @return
     */
    @GetMapping("/index")
    public String index(){
        //从redis缓存中获取用户信息
        User user = (User) redisTemplate.opsForValue().get("user");
        return "index";
    }

    /**
     * 跳转到登录页面
     * @return
     */
    @GetMapping("/login")
    public String login(HttpServletRequest request){
        //清空session
        String error = request.getParameter("error");
        if(error==null){
            request.getSession().invalidate();
        }
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
            for(ObjectError error : allErrors){
                System.out.println(error.getCode()+"---"+error.getArguments()+"---"+error.getDefaultMessage());
            }
        }
        userService.userRegister(user);
        return "redirect:/index";
    }
}
