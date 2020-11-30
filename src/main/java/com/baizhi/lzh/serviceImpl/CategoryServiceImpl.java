package com.baizhi.lzh.serviceImpl;

import com.baizhi.lzh.annotation.AddLog;
import com.baizhi.lzh.annotation.DelCache;
import com.baizhi.lzh.annotation.RedisCache;
import com.baizhi.lzh.dao.CategoryDAO;
import com.baizhi.lzh.entity.Category;
import com.baizhi.lzh.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDAO categoryDAO;

    @Transactional(propagation = Propagation.SUPPORTS)
    @RedisCache
    @Override
    public HashMap<String, Object> queryAllFirstCategory(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();

        Integer start = (page-1)*rows;
        Integer stop = page*rows;
        List<Category> categories = categoryDAO.queryAllFirstCategory(start, stop);

        Integer counts = categoryDAO.counts("1");

        //总页数
        Integer total = counts % rows == 0 ? counts/rows : counts/rows+1;

        map.put("page",page);//当前页
        map.put("rows",categories);//查询到的记录
        map.put("total",total);//页数
        map.put("records",counts);//总记录数

        return map;
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    @RedisCache
    @Override
    public HashMap<String, Object> queryAllSecondCategory(String id, Integer page, Integer rows) {

        HashMap<String, Object> map = new HashMap<>();

        Integer start = (page-1)*rows;
        Integer stop = page*rows;
        List<Category> categories = categoryDAO.queryAllSecondCategory(id, start, stop);

        Integer twoCategoryCounts = categoryDAO.twoCategoryCounts("2", id);

        //总页数
        Integer totals = twoCategoryCounts % rows == 0 ? twoCategoryCounts/rows : twoCategoryCounts/rows+1;

        map.put("page",page);//当前页
        map.put("rows",categories);//查询到的记录
        map.put("total",totals);//页数
        map.put("records",twoCategoryCounts);//总记录数

        return map;
    }

    @AddLog("添加类别信息")
    @DelCache
    @Override
    public void addCategory(Category category) {
        category.setId(UUID.randomUUID().toString());

        //如果前台传过来的是父类id为空 则为1级类别 否则反之
        if(category.getParentId()==null){
            category.setLevels("1");
        }else{
            category.setLevels("2");
        }

        categoryDAO.addCategory(category);
    }


    @AddLog("修改类别信息")
    @DelCache
    @Override
    public void updateCategory(Category category) {
        categoryDAO.updateCategory(category);
    }


    @AddLog("删除类别信息")
    @DelCache
    @Override
    public Map<String,Object> deleteCategory(String id) {

        Map<String,Object> map = new HashMap<>();

        String message = null;
        String status = null;

        Integer categoryCounts = categoryDAO.categoryCounts(id);

        if(categoryCounts==0){
            categoryDAO.deleteCategory(id);
            message = "删除成功";
            status = "200";
        }else{
             message = "该一级类别下有对应二级类别";
             status = "201";
        }
        map.put("status",status);
        map.put("message",message);

        return map;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @RedisCache
    @Override
    public List<Category> queryFirstCategory() {
        return categoryDAO.queryFirstCategory();
    }


    //查所有一级类别及对应二级类别
    @RedisCache
    @Override
    public List<Category> queryAllCategory() {
        return categoryDAO.queryAllCategory();
    }
}


