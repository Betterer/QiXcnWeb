package com.qixcnweb.qixian.dao;

import com.qixcnweb.qixian.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dingxiaochi on 2018/3/17.
 */
public interface UserDao  extends CrudRepository<User, Long> {

    /** ------------------------------------------------- 增 -------------------------------------------------------- **/

    /** ------------------------------------------------- 删 -------------------------------------------------------- **/

    /** ------------------------------------------------- 改 -------------------------------------------------------- **/

    /** ------------------------------------------------- 查 -------------------------------------------------------- **/

    //根据用户名查询用户
    User findUserByName(String name);
    //根据用户名和密码查询用户
    User findUserByNameAndAndPassword(String name,String password);

}
