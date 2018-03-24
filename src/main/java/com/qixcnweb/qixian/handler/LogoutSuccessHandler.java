package com.qixcnweb.qixian.handler;

import com.qixcnweb.qixian.dao.UserDao;
import com.qixcnweb.qixian.domain.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 使用Spring security登出成功之后做的操作
 * Created by dingxiaochi on 2018/3/9.
 */
public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    @Resource
    private RedisTemplate redisTemplate;


    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //将用户信息从redis中清除
        redisTemplate.delete("user");
        //session清除
        HttpSession session = httpServletRequest.getSession();
        session.invalidate();
        super.onLogoutSuccess(httpServletRequest, httpServletResponse, authentication);
    }
}
