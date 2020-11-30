package com.baizhi.lzh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {
    /**
     id
     */
    private String id;

    /**
     类别名
     */
    private String cateName;

    /**
     级别
     */
    private String levels;

    /**
     父类id
     */
    private String parentId;

    private List<Category> categoryList;
}

