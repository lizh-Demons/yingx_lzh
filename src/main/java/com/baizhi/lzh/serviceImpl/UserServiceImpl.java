package com.baizhi.lzh.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.baizhi.lzh.annotation.AddLog;
import com.baizhi.lzh.annotation.DelCache;
import com.baizhi.lzh.annotation.RedisCache;
import com.baizhi.lzh.dao.UserDAO;
import com.baizhi.lzh.entity.Citys;
import com.baizhi.lzh.entity.Distribution;
import com.baizhi.lzh.entity.Statistics;
import com.baizhi.lzh.entity.User;
import com.baizhi.lzh.service.UserService;
import com.baizhi.lzh.util.MonthUtil;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    //    依赖于dao
    @Autowired
    private UserDAO userDAO;
    @Autowired
    HttpServletRequest request;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    @RedisCache
    public List<User> showPage(Integer page, Integer rows) {

        Integer start = (page - 1) * rows;
        Integer stop = page * rows;
        List<User> users = userDAO.showPage(start, stop);

        return users;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer queryTotals() {
        return userDAO.queryTotals();
    }


    @AddLog("修改用户状态")
    @DelCache
    @Override
    public Map<String, Object> updateStatus(String status, String id) {

        Map<String, Object> map = new HashMap<>();
        try {
            status = status.equals("0") ? "1" : "0";
            userDAO.updateStatus(status, id);
            map.put("status", 200);
            map.put("message", "修改成功");
        } catch (Exception e) {
            map.put("status", 201);
            map.put("message", "修改失败");
            e.printStackTrace();
        }
        return map;
    }


//    //获取用户信息  进行统计
//    @RedisCache
//    @Transactional(propagation = Propagation.SUPPORTS)
//    @Override
//    public Map<String,Object> getUserData() {
//        Map<String,Object> map = new HashMap<>();
//        List<Statistics> man = userDAO.getUserData("男");
//        System.out.println(man);
//        List<Statistics> woman = userDAO.getUserData("女");
//        System.out.println(woman);
//        map.put("man",man);
//        map.put("woman",woman);
//        return map;
//    }



    //获取用户信息  导出导入用户信息
    @Transactional(propagation = Propagation.SUPPORTS)
    @RedisCache
    @Override
    public List<User> queryAllUser() {
        List<User> users = userDAO.queryAllUser();

        for (User user : users) {
            String realPath = request.getSession().getServletContext().getRealPath(user.getPicImg());
            user.setPicImg(realPath);
        }
        return users;
    }



    //获取用户分布信息
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public  List<Distribution> getDistribution() {
        List<Statistics> distribution = userDAO.getDistribution();
        List<Citys> citys = new ArrayList<>();
        List<Citys> citys2 = new ArrayList<>();
        Distribution d1 = new Distribution();
        Distribution d2 = new Distribution();
        List<Distribution> dList = new ArrayList<>();
        for (Statistics statistics : distribution) {
            if(statistics.getSex().equals("女")){
                Citys city = new Citys();
                city.setName(statistics.getCity());
                city.setValue(statistics.getCounts());
                citys.add(city);
            }else{
                Citys city = new Citys();
                city.setName(statistics.getCity());
                city.setValue(statistics.getCounts());
                citys2.add(city);
            }

        }
        d1.setSex("女");
        d1.setCitys(citys);
        d2.setSex("男");
        d2.setCitys(citys2);

        dList.add(d1);
        dList.add(d2);
        return dList;

    }

    //统计用户信息
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String,Object> queryByMonth() {
        Map<String,Object> map = new HashMap<>();

        //获取月份 转换为数字


        //创建三个集合  存放后台需要的数据
        ArrayList<Integer> boy = new ArrayList<>();    // 小男孩
        ArrayList<Integer> girl = new ArrayList<>();  // 小女孩
        ArrayList<String> months = new ArrayList<>(); // 月

//        String date = new SimpleDateFormat("MM").format(new Date());
//        Integer integer = Integer.valueOf(date);
        for (int i = 1; i <= 12; i++) {
            System.out.println(i);
            boy.add(userDAO.queryByMonth("男",i));
            girl.add(userDAO.queryByMonth("女",i));
            months.add(i+"月");

        }
        map.put("boy",boy);
        map.put("girl",girl);
        map.put("month",months);



        return map;
    }
}
