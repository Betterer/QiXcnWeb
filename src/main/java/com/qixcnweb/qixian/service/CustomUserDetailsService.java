package com.qixcnweb.qixian.service;

import com.qixcnweb.qixian.dao.UserDao;
import com.qixcnweb.qixian.domain.User;
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

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userDao.findUserByName(name);
        if (user == null) {
            throw new UsernameNotFoundException("Account " + name + " not found");
        }

        //储存用户角色名称
        Collection<SimpleGrantedAuthority> authorityList = new ArrayList<SimpleGrantedAuthority>();
//        for(Role role : staff.getRoleList()){
//            authorityList.add(new SimpleGrantedAuthority(role.getName()));
//        }

        //返回包括权限角色的User给security
        org.springframework.security.core.userdetails.User auth_user = new
                org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(),authorityList);
        return auth_user;
    }
}
