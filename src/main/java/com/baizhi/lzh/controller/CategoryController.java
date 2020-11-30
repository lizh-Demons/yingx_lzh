package com.baizhi.lzh.controller;


import com.alibaba.druid.util.StringUtils;
import com.baizhi.lzh.annotation.AddLog;
import com.baizhi.lzh.entity.Category;
import com.baizhi.lzh.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("category")
public class CategoryController {

    //依赖于业务
    @Autowired
    private CategoryService categoryService;


    //分页查询
    @ResponseBody
    @RequestMapping("firstCategory")
    public HashMap<String,Object> queryPageByFirstCategory(Integer page, Integer rows){

        return categoryService.queryAllFirstCategory(page, rows);
    }


    @ResponseBody
    @RequestMapping("secondCategory")
    public HashMap<String,Object> secondCategory(String id,Integer page, Integer rows){

        return categoryService.queryAllSecondCategory(id, page, rows);
    }

    @RequestMapping("edit")
    @ResponseBody
    public Map<String,Object> edit(Category category,String oper){
        Map<String,Object> map = new HashMap<>();
        if(StringUtils.equals("add",oper)){
            categoryService.addCategory(category);
        }
        if(StringUtils.equals("edit",oper)){
            categoryService.updateCategory(category);
        }
        if(StringUtils.equals("del",oper)){
            map = categoryService.deleteCategory(category.getId());
//            log.debug("类别删除状态", );
        }
        return map;
    }

    @RequestMapping("queryCategory")
    @ResponseBody
    public void queryCategory(HttpServletResponse response){
        List<Category> categories = categoryService.queryFirstCategory();
        StringBuilder sb = new StringBuilder();
        sb.append("<select>");
        categories.forEach(category -> {
            sb.append("<option value="+category.getId()+">"+ category.getCateName() +"</option>");
        });
        sb.append("</select>");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        try {
            response.getWriter().println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
