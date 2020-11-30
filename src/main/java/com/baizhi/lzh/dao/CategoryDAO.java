package com.baizhi.lzh.dao;

import com.baizhi.lzh.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryDAO {

    //查询所有一级类别
    List<Category> queryAllFirstCategory(@Param("start")Integer start, @Param("stop") Integer stop);

    //查询总条数
    Integer counts(@Param("levels")String levels);


    //根据父类id查询对应二级类别
    List<Category> queryAllSecondCategory(@Param("id")String id,@Param("start")Integer start, @Param("stop") Integer stop);

    //为二级类别提供查询总条数
    Integer twoCategoryCounts(@Param("levels")String levels,@Param("id")String id);

    //添加一级类别
    void  addCategory(Category category);
    //修改类别
    void updateCategory(Category category);
    //删除类别
    void deleteCategory(@Param("id") String id);


    //为修改添加提供查所有一级类别
    List<Category> queryFirstCategory();

    //为删除一级类别id提供查询一级类别id下有没有二级类别
    Integer categoryCounts(@Param("id")String id);

    //前台:展示一级类别及对应二级类别
    List<Category> queryAllCategory();
}
