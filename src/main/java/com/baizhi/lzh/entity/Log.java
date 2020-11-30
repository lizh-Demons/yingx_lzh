package com.baizhi.lzh.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Log  implements Serializable {
    /**
     id
     */
    private String id;

    /**
     用户名
     */
    private String name;

    /**
     日志时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date times;

    /**
     事件
     */
    private String event;

    /**
     状态  结果
     */
    private String status;

}

