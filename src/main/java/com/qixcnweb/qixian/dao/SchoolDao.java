package com.qixcnweb.qixian.dao;

import com.qixcnweb.qixian.domain.School;
import com.qixcnweb.qixian.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by dingxiaochi on 2018/3/17.
 */
public interface SchoolDao extends CrudRepository<School, Long>, PagingAndSortingRepository<School,Long> {


    /** ------------------------------------------------- 增 -------------------------------------------------------- **/

    /** ------------------------------------------------- 删 -------------------------------------------------------- **/

    /** ------------------------------------------------- 改 -------------------------------------------------------- **/

    /** ------------------------------------------------- 查 -------------------------------------------------------- **/

    School findSchoolByUser(User user);

    School findSchoolByUserId(Integer userId);

    School findSchoolById(Integer schoolId);

    Page<School> findAll(Pageable pageable);

    Page<School> findSchoolByNameContaining(String name, Pageable pageable);

    Page<School> findSchoolByTelephoneContaining(String telephone, Pageable pageable);

    Page<School> findSchoolByEmailContaining(String email, Pageable pageable);


}
