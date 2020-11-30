package com.baizhi.lzh.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoPO implements Serializable {
//
//    "id": "a11282389568441fa166ebedef03e530",
//            "videoTitle": "人民的名义",
//            "cover": "http://q40vnlbog.bkt.clouddn.com/1578650041065_人民的名义.jpg",
//            "path": "http://q3th1ypw9.bkt.clouddn.com/1578650041065_人民的名义.mp4",
//            "uploadTime": "2020-01-30",
//            "description": "人民的名义",
//            "likeCount": 0,      视频数据
//            "cateName": "Java",   类别名    用户头像
//            "userPhoto":"http://q40vnlbog.bkt.clouddn.com/1.jpg"
    private String id;
    private String videoTitle;
    private String cover;
    private String path;
    private Date uploadTime;
    private String description;//
    private Integer likeCount;//点赞数
    private String cateName;//类别名
    private String userPhoto;//头像


}
