package com.qixcnweb.qixian.dao;

import com.qixcnweb.qixian.domain.Category;
import com.qixcnweb.qixian.domain.Lesson;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by dingxiaochi on 2018/4/4.
 */
public interface CategoryDao extends CrudRepository<Category, Long> {

    /** ------------------------------------------------- 增 -------------------------------------------------------- **/

    /** ------------------------------------------------- 删 -------------------------------------------------------- **/

    /** ------------------------------------------------- 改 -------------------------------------------------------- **/

    /** ------------------------------------------------- 查 -------------------------------------------------------- **/

    //查询所有一级类目
    List<Category> findAllByLevel(Integer level);

    //根据ID查询类目
    Category findCategoryById(Integer categoryId);

    //根据父级ID查询类目
    List<Category> findCategoryByParentCategoryId(Integer parentId);


}
