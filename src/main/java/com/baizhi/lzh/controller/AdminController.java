package com.baizhi.lzh.controller;

import com.baizhi.lzh.entity.Admin;
import com.baizhi.lzh.service.AdminService;
import com.baizhi.lzh.util.ImageCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping("admin")
public class AdminController {

    //    依赖业务
    @Autowired
    private AdminService adminService;


    @RequestMapping("login")
    public String login(Admin admin, HttpServletRequest request, String clientCode) {

        String message = adminService.login(admin, clientCode, request);
        if (message.equals("success")) {
            return "redirect:/main/main.jsp";
        } else {
            request.getSession().setAttribute("message",message);
            return "redirect:/login/login.jsp";
        }
    }

    //生成验证码
    @RequestMapping("getImageCode")
    public String getImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1 获取验证码随机字符
        String code = ImageCodeUtil.getSecurityCode();
        System.out.println("验证码:"+code);
        BufferedImage bufferedImage = ImageCodeUtil.createImage(code);
        ServletOutputStream out = response.getOutputStream();
        // 2 使用响应输出流 把bufferedImage 输出到 client
        ImageIO.write(bufferedImage, "jpg", out);
        // 3 把随机验证码，存入session作用域
        HttpSession session = request.getSession();
        session.setAttribute("serverCode", code);
        return null;
    }

    //安全退出
    @RequestMapping("exit")
    public String exit(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/login/login.jsp";
    }
}
