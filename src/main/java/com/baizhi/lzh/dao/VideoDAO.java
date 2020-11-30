package com.baizhi.lzh.dao;

import com.baizhi.lzh.entity.Statistics;
import com.baizhi.lzh.entity.Video;
import com.baizhi.lzh.po.VideoPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoDAO {


    //文件上传----数据入库
    void addVideo(Video video);

    //文件删除----删除库中数据
    void deleteVideo(String id);

    //文件修改----修改数据库数据
    void updateVideo(Video video);

    //文件上传成功---根据id去修改文件路径
    void updateVideoPath(@Param("id") String id, @Param("videoPath") String videoPath,@Param("coverPath")String coverPath);

    //修改视频状态
    void updateVideoStatus(@Param("id") String id, @Param("status") String status);

    //分页查询
    List<Video>  videoPage(@Param("start")Integer start,@Param("stop") Integer stop);


    //为分页查询提供查询总条数
    Integer counts();

    //根据id查一个
    Video queryOne(String id);


    //前台展示视频信息
    List<VideoPO> queryByReleaseTime();

    //前台:  模糊查询视频信息
    List<VideoPO> queryByLikeVideoName(@Param("content")String content);


}
