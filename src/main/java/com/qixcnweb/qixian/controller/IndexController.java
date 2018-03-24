package com.qixcnweb.qixian.controller;

import ch.qos.logback.core.joran.util.StringToObjectConverter;
import com.qixcnweb.qixian.domain.User;
import com.qixcnweb.qixian.remote.TestFeignClient;
import com.qixcnweb.qixian.service.UserService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by dingxiaochi on 2018/3/17.
 */
@Controller
public class IndexController {

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private UserService userService;



    @Resource
    private TestFeignClient testFeignClient;        //测试eureka远程调用

    /**
     * 跳转到index页面
     * @return
     */
    @GetMapping("/index")
    public String index(HttpServletRequest request, Map<String,Object> resultMap){
        User user = (User) request.getSession().getAttribute("user");
        //从redis缓存中获取用户信息
        //User user = (User) redisTemplate.opsForValue().get("user");
        resultMap.put("currentUser",user);
        System.out.println(user);
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


    /**
     * 测试eureka远程调用,发送邮件
     * @return
     */
    @GetMapping("/sendEmail")
    @ResponseBody
    public String sendEmail(){
       return testFeignClient.sendEmail();
    }

    /**
     * 测试eureka远程调用,发送邮件
     * @return
     */
    @GetMapping("/test")
    @ResponseBody
    public String testEmail(){
        return testFeignClient.testEmail();
    }


}
