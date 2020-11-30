package com.baizhi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.baizhi.lzh.dao")
@SpringBootApplication
public class YingxLizhApplication {

    public static void main(String[] args) {
        SpringApplication.run(YingxLizhApplication.class, args);
    }

}
