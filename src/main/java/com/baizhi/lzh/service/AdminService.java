package com.baizhi.lzh.service;

import com.baizhi.lzh.entity.Admin;

import javax.servlet.http.HttpServletRequest;

public interface AdminService {

    //登录
    String login(Admin admin, String clientCode, HttpServletRequest request);
}
