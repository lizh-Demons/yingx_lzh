package com.baizhi.lzh.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.lzh.annotation.AddLog;
import com.baizhi.lzh.annotation.RedisCache;
import com.baizhi.lzh.dao.AdminDAO;
import com.baizhi.lzh.entity.Admin;
import com.baizhi.lzh.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {

    //    依赖于dao

    @Autowired
    private AdminDAO adminDAO;


//    @AddLog("管理员登录")
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public String login(Admin admin, String clientCode, HttpServletRequest request) {
        String message = null;
        Admin one = adminDAO.findOne(admin.getUsername());
        HttpSession session = request.getSession();
        System.out.println("用户session"+session);
        String serverCode = session.getAttribute("serverCode").toString();
        if (clientCode.equals(serverCode)) {
            if (one != null) {
                if (one.getPassword().equals(admin.getPassword())) {
                    message = "success";
                    //将正确的用户存到session作用域中
                    session.setAttribute("admin", one);
                } else {
                    message = "密码错误";
                }
            } else {
                message = "该用户不存在";
            }
        } else {
            message = "验证码错误";
        }
        return message;
    }
}
