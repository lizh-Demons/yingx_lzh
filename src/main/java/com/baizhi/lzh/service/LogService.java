package com.baizhi.lzh.service;

import com.baizhi.lzh.entity.Log;

import java.util.Map;

public interface LogService {

    Map<String,Object> logPage(Integer page, Integer rows);

    //删除日志信息
    void delete(String id);
    //添加日志
    void addLog(Log log);


}
