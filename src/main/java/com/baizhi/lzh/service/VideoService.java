package com.baizhi.lzh.service;

import com.baizhi.lzh.entity.Log;
import com.baizhi.lzh.entity.Video;
import com.baizhi.lzh.po.VideoPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface VideoService {

    //文件上传----数据入库   返回视频id
    String addVideo(Video video);

    //文件上传成功---根据id去修改文件路径
    void updateVideoPath(String id, String videoPath);

    //文件上传   参数id 为文件名修改提供id
    void videoUpload(MultipartFile videoPath, String id);

    //分页查询
    Map<String,Object> videoPage(Integer page,Integer rows);

    //文件上传   参数id 为文件名修改提供id
    void videoUploadAliYun(MultipartFile videoPath, String id);


    //文件删除----删除库中数据
    void deleteVideo(String id);

    //前台展示视频信息
    List<VideoPO> queryByReleaseTime();

    //前台:  模糊查询视频信息
    List<VideoPO> queryByLikeVideoName(String content);

}
