package com.qixcnweb.qixian.service;

import com.qixcnweb.qixian.dao.UserDao;
import com.qixcnweb.qixian.domain.Role;
import com.qixcnweb.qixian.domain.User;
import com.qixcnweb.qixian.utils.CommonUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Spring Security 使用的service,用于读取账户信息
 * Created by dingxiaochi on 2018/3/7.
 */
public class CustomUserDetailsService implements UserDetailsService {

    @Resource
    private UserDao userDao;

    @Resource
    private CommonUtils commonUtils;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = null;
        if(commonUtils.isPhone(name)){     //如果输入账号是电话号码格式
            user = userDao.findUserByPhone(name);
        }else if(commonUtils.isEmail(name)){   //如果输入账号是电子邮箱格式
            user = userDao.findUserByEmail(name);
        }else{          //如果是普通账号
            user = userDao.findUserByName(name);
        }

        //如果用户不存在
        if (user == null) {
            throw new UsernameNotFoundException("user is not exist!");
        }

        //储存用户角色名称
        Collection<SimpleGrantedAuthority> authorityList = new ArrayList<SimpleGrantedAuthority>();
        for(Role role : user.getRoleList()){
            authorityList.add(new SimpleGrantedAuthority(role.getName()));
        }

        //返回包括权限角色的User给security
        org.springframework.security.core.userdetails.User auth_user = new
                org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(),authorityList);
        return auth_user;
    }
}
