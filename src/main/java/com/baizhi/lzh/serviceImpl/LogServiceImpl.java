package com.baizhi.lzh.serviceImpl;

import com.baizhi.lzh.annotation.DelCache;
import com.baizhi.lzh.annotation.RedisCache;
import com.baizhi.lzh.dao.LogDAO;
import com.baizhi.lzh.entity.Feedback;
import com.baizhi.lzh.entity.Log;
import com.baizhi.lzh.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("logService")
@Transactional
public class LogServiceImpl implements LogService {

    //依赖于业务
    @Autowired
    private LogDAO logDAO;


    @Transactional(propagation = Propagation.SUPPORTS)
    @RedisCache
    @Override
    public Map<String, Object> logPage(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();

//        计算开始页数    展示行数
        Integer start = (page - 1) * rows;
        Integer stop = page * rows;

        //计算总页数
        Integer counts = logDAO.queryTotals();
        Integer total = counts % rows == 0 ? counts / rows : counts / rows + 1;

        //计算以后添加到map
        List<Log> logs = logDAO.logPage(start, stop);
        map.put("rows", logs);
        map.put("page", page);
        map.put("total", total);
        map.put("records",counts );

        return map;
    }



    //删除日志信息
    @Override
    @DelCache
    public void delete(String id) {
        logDAO.delete(id);
    }

    //添加日志
    @Override
    @DelCache
    public void addLog(Log log) {
        logDAO.addLog(log);
    }
}
