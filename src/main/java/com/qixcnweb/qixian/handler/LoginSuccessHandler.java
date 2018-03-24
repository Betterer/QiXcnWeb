package com.qixcnweb.qixian.handler;

import com.qixcnweb.qixian.dao.UserDao;
import com.qixcnweb.qixian.domain.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 使用Spring security登录成功之后做的操作
 * Created by dingxiaochi on 2018/3/9.
 */
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Resource
    private UserDao userDao;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //获取登录后的用户账号
        String name = authentication.getName();
        //根据用户账号查询出用户信息
        User user = userDao.findUserByName(name);
        //将用户信息存在redis缓存中,方便使用
        redisTemplate.opsForValue().set("user",user);
        //session中也存入User信息
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("user",user);

        super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);

    }
}
