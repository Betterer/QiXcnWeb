package com.qixcnweb.qixian.service;

import com.qixcnweb.qixian.constant.Constant;
import com.qixcnweb.qixian.dao.CategoryDao;
import com.qixcnweb.qixian.domain.Category;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dingxiaochi on 2018/4/4.
 */
@Service
public class CategoryService {

    @Resource
    private CategoryDao categoryDao;
    /**
     * 查询出所有类目,根据类目等级
     * @param level
     * @return
     */
    public List<Category> findAllByLevel(Integer level){
        List<Category> categoryList = categoryDao.findAllByLevel(level);
        return categoryList;
    }

    /**
     * 根据类ID查询类目
     * @param categoryId
     * @return
     */
    public Category findCategoryById(Integer categoryId) {
        Category category = categoryDao.findCategoryById(categoryId);
        return category;
    }
}
