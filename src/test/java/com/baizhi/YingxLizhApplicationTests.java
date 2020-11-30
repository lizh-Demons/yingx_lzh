package com.baizhi;

import com.baizhi.lzh.dao.AdminDAO;
import com.baizhi.lzh.dao.UserDAO;
import com.baizhi.lzh.entity.Admin;
import com.baizhi.lzh.entity.User;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class YingxLizhApplicationTests {

    @Autowired
    private AdminDAO adminDAO;

    @Autowired
    private UserDAO userDAO;
    @Test
    void contextLoads() {
//        Admin admin = adminDAO.findOne("admin");
//        System.out.println(admin);
        List<User> users = userDAO.showPage(0,2);
        users.forEach(user -> System.out.println(user));
    }

}
