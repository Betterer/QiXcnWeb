package com.qixcnweb.qixian.handler;

import com.qixcnweb.qixian.dao.UserDao;
import com.qixcnweb.qixian.domain.User;
import com.qixcnweb.qixian.utils.FileUploadUtils;
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

    @Resource
    private FileUploadUtils fileUploadUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //获取登录后的用户账号
        String name = authentication.getName();
        //根据用户账号查询出用户信息
        User user = userDao.findUserByName(name);
        //如果用户有头像的话,生成头像的OSS访问URL
        if(user.getImage()!=null){
            //OSS访问url过期时间
            Integer overtime = 1000*60*60*24; //过期时间设置为24小时
            String fileUrl = fileUploadUtils.getFileUrl(user.getImage(), overtime);
            user.setImage(fileUrl);
        }
        //将用户信息存在redis缓存中,方便使用
        redisTemplate.opsForValue().set("user",user);
        //session中也存入User信息
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("user",user);

        super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);

    }
}
