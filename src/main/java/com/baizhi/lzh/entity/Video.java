package com.baizhi.lzh.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

//set+get toString
@Data
//无参构造
@NoArgsConstructor
//有参构造
@AllArgsConstructor
public class Video implements Serializable {
    /**
     视频id
     */
    private String id;

    /**
     标题
     */
    private String title;

    /**
     简介
     */
    private String brief;

    /**
     封面
     */
    private String coverPath;

    /**
     视频
     */
    private String videoPath;

    /**
     上传时间
     */

    //设置日期接收时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    //设置日期响应格式
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date uploadTime;
    /**
     点赞数
     */
    private String likeCount;

    /**
     播放次数
     */
    private String playCount;

    /**
     所属类别id
     */
    private String categoryId;

    /**
     用户id
     */
    private String userId;

    /**
     分组id
     */
    private String groupId;

    /**
     状态
     */
    private String status;

}

