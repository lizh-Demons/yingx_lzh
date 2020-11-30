package com.baizhi.lzh.entity;

import java.io.Serializable;
import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User  implements Serializable {
    /**
     用户id
     */
    @Excel(name = "id")
    private String id;

    /**
     图片路径
     */
    @Excel(name = "图片",type = 2)
    private String picImg;

    /**
     昵称
     */
    @Excel(name = "昵称")
    private String nickName;

    /**
     密码
     */
    @Excel(name = "密码")
    private String password;

    /**
     状态
     */
    @Excel(name = "密码")
    private String status;

    /**
     手机号
     */
    @Excel(name = "手机号")
    private String phone;

    /**
     创建日期
     */

    //设置日期接格式
    @JsonFormat(pattern = "yyyy-MM-dd")
    //设置日期响应格式
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    @Excel(name = "日期",importFormat = "yyyy-MM-dd",exportFormat = "yyyy-MM-dd")
    private Date createDate;

    /**
     简介
     */
    @Excel(name = "简介")
    private String brief;

    /**
     学分
     */
    @Excel(name = "学分")
    private Integer score;

    @Excel(name = "性别")
    private String sex;
}

