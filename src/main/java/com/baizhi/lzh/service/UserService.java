package com.baizhi.lzh.service;

import com.baizhi.lzh.entity.Distribution;
import com.baizhi.lzh.entity.Statistics;
import com.baizhi.lzh.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserService {

    //分页查询
    List<User> showPage(@Param("page") Integer page, @Param("rows") Integer rows);

    //查数量
    Integer queryTotals();

    //根据用户id修改用户状态
    Map<String,Object> updateStatus(String status, String id);

//    //获取用户信心 进行统计
//    Map<String,Object> getUserData();

    //查所有用户信息
    List<User> queryAllUser();


    //查询用户分布信息
    List<Distribution> getDistribution();

    //获取用户信心 进行统计
    Map<String,Object> queryByMonth();
}
