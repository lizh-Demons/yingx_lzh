package com.baizhi;

import com.baizhi.lzh.dao.CategoryDAO;
import com.baizhi.lzh.dao.UserDAO;
import com.baizhi.lzh.dao.VideoDAO;
import com.baizhi.lzh.entity.Category;
import com.baizhi.lzh.entity.Statistics;
import com.baizhi.lzh.entity.User;
import com.baizhi.lzh.po.VideoPO;
import com.baizhi.lzh.service.CategoryService;
import com.baizhi.lzh.util.MonthUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class CategoryTest {

    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private VideoDAO videoDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private CategoryService categoryService;
    @Test
    void test(){
////        List<Category> categories = categoryDAO.queryAllFirstCategory(0,2);
////        categories.forEach(category -> System.out.println(category));
////        Integer counts = categoryDAO.counts("2");
////        System.out.println(counts);
//
////        HashMap<String, Object> map = categoryService.queryAllFirstCategory(1,2);
////        System.out.println(map.containsKey("total"));
////
////        List<Category> categories = categoryService.queryFirstCategory();
////        categories.forEach(category -> System.out.println(category));
//
//        Integer integer = categoryDAO.categoryCounts("1");
//        System.out.println(integer);


//        List<VideoPO> videoPOS = videoDAO.queryByReleaseTime();
//        List<VideoPO> videoPOS1 = videoPOS;
//        System.out.println(videoPOS1);

//
//        List<Category> categories = categoryService.queryAllCategory();
//        System.out.println(categories);

//        List<Statistics> userData = userDAO.getUserData();
//        userData.forEach(user-> System.out.println(user));
//        List<User> users = userDAO.queryAllUser();
//        for (User user : users) {
//            System.out.println(user);
        //        }

//        List<Statistics> distribution = userDAO.getDistribution();
//        distribution.forEach(dis-> System.out.println(dis));


//        List<Statistics> man = userDAO.getUserData("男");
//        System.out.println(man);

        //获取月份 转换为数字
        List<Integer> integers = MonthUtil.queryMonths();
        System.out.println(integers);
        System.out.println("第一次提交");
    }
}
