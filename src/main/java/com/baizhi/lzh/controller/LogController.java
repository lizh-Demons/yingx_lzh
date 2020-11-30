package com.baizhi.lzh.controller;


import com.alibaba.druid.util.StringUtils;
import com.baizhi.lzh.entity.Log;
import com.baizhi.lzh.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("log")
public class LogController {

    //依赖于业务
    @Autowired
    private LogService logService;


    //分页查询
    @RequestMapping("logPage")
    @ResponseBody
    public Map<String,Object> logPage(Integer page, Integer rows){
        Map<String, Object> map = logService.logPage(page, rows);
        return map;
    }


    @RequestMapping("edit")
    @ResponseBody
    public void edit(Log log,String oper){
        if(StringUtils.equals("del",oper)){
            logService.delete(log.getId());
        }
    }
}
