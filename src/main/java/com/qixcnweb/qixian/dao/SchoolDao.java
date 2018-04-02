package com.qixcnweb.qixian.dao;

import com.qixcnweb.qixian.domain.School;
import com.qixcnweb.qixian.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dingxiaochi on 2018/3/17.
 */
public interface SchoolDao extends CrudRepository<School, Long> {


    /** ------------------------------------------------- 增 -------------------------------------------------------- **/

    /** ------------------------------------------------- 删 -------------------------------------------------------- **/

    /** ------------------------------------------------- 改 -------------------------------------------------------- **/

    /** ------------------------------------------------- 查 -------------------------------------------------------- **/

    School findSchoolByUser(User user);

    School findSchoolByUserId(Integer userId);

    School findSchoolById(Integer schoolId);
}
