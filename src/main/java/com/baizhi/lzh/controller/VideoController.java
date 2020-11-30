package com.baizhi.lzh.controller;


import com.alibaba.druid.util.StringUtils;
import com.baizhi.lzh.entity.Video;
import com.baizhi.lzh.service.VideoService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.ProgressBarUI;
import java.util.Map;

@Controller
@RequestMapping("video")
public class VideoController {

    //依赖于业务
    @Autowired
    private VideoService videoService;

    //分页查询
    @RequestMapping("videoPage")
    @ResponseBody
    public Map<String,Object> videoPage(Integer page,Integer rows){
        Map<String, Object> map = videoService.videoPage(page, rows);
        return map;
    }

    @RequestMapping("edit")
    @ResponseBody
    public String edit(Video video,String oper){
        String result = null;

        if(StringUtils.equals("add",oper)){
            result = videoService.addVideo(video);
        }

        if(StringUtils.equals("del",oper)){
            System.out.println(video.getId());
            videoService.deleteVideo(video.getId());
        }
        if(StringUtils.equals("edit",oper)){

        }
        return  result;

    }


    //文件上传
    @RequestMapping("videoUpload")
    public void videoUpload(MultipartFile videoPath,String id){
        videoService.videoUploadAliYun(videoPath,id);
    }
}
