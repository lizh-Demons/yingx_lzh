package com.baizhi.lzh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//set+get  to String
@Data
//无参构造
@NoArgsConstructor
//有参构造
@AllArgsConstructor
public class Admin implements Serializable {

    private String id;
    private String username;
    private String password;

}
