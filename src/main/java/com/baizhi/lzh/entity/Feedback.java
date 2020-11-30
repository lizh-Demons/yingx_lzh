package com.baizhi.lzh.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback implements Serializable {
    /**
     反馈
     id
     */
    private String id;

    /**
     标题
     */
    private String title;

    /**
     内容
     */
    private String content;

    /**
     用户id
     */
    private String userId;

    /**
     反馈时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date feedbackTime;

}

