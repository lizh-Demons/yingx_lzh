package com.baizhi.lzh.dao;

import com.baizhi.lzh.entity.Admin;

public interface AdminDAO {
    Admin findOne(String username);
}
