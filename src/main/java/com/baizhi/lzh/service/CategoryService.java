package com.baizhi.lzh.service;


import com.baizhi.lzh.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CategoryService {
    //查询所有一级类别
    HashMap<String,Object> queryAllFirstCategory(Integer page, Integer rows);
    //根据父类id查询对应二级类别
    HashMap<String,Object> queryAllSecondCategory(String id,Integer start,Integer stop);
    //添加一级类别
    void  addCategory(Category category);
    //修改类别
    void updateCategory(Category category);
    //删除类别
    Map<String,Object> deleteCategory(@Param("id") String id);

    //为修改添加提供查所有一级类别
    List<Category> queryFirstCategory();

    //查所有类别
    List<Category> queryAllCategory();
}
