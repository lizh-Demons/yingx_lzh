package com.baizhi.lzh.dao;

import com.baizhi.lzh.entity.Log;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogDAO {

    //添加日志
    void addLog(Log log);

    //分页查询日志

    List<Log> logPage(@Param("start")Integer start,@Param("stop")Integer stop);

    //查数量
    Integer queryTotals();

    //删除日志信息
    void delete(String id);

}
