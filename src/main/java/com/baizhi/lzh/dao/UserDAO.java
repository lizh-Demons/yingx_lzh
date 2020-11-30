package com.baizhi.lzh.dao;

import com.baizhi.lzh.entity.Statistics;
import com.baizhi.lzh.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDAO {

    //分页查询
    List<User> showPage(@Param("start")Integer start,@Param("end")Integer end);
    //查数量
    Integer queryTotals();

    //根据用户id修改用户状态
    void updateStatus(@Param("status") String status,@Param("id") String id);

//    //获取用户信心 进行统计    废弃
//    List<Statistics> getUserData(@Param("sex")String sex);

    //查所有用户信息
    List<User> queryAllUser();

    //查询用户分布信息
    List<Statistics> getDistribution();

    //通过性别  月份进行查询用户数量  进行统计
    Integer queryByMonth(@Param("sex") String sex, @Param("month") Integer month);


}
