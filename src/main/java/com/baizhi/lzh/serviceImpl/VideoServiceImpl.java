package com.baizhi.lzh.serviceImpl;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.baizhi.lzh.annotation.AddLog;
import com.baizhi.lzh.annotation.RedisCache;
import com.baizhi.lzh.dao.VideoDAO;
import com.baizhi.lzh.entity.Video;
import com.baizhi.lzh.po.VideoPO;
import com.baizhi.lzh.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

import static com.baizhi.lzh.util.AliyunOSSUtil.uploadFileNetIO;

@Service("videoService")
@Transactional
public class VideoServiceImpl implements VideoService {

    //依赖于dao
    @Autowired
    private VideoDAO videoDAO;
    @Autowired
    HttpServletRequest request;


    @AddLog("添加视频")
    @Override
    public String addVideo(Video video) {
        String uuid = UUID.randomUUID().toString();
        video.setId(uuid);
        video.setVideoPath("1");
        video.setPlayCount("1");
        video.setGroupId("1");
        video.setUploadTime(new Date());
        video.setUserId("1");
        video.setCategoryId("c8541b6a-50e0-4c9a-a2a0-07c4de9f9318");
        //添加用户id
//        Object admin = request.getSession().getAttribute("admin");
        videoDAO.addVideo(video);


//       ,title,coverPath,uploadTime,
//        brief,likeCount,categoryId,userId,status;


        return uuid;
    }

    @AddLog("修改视频信息")
    @Override
    public void updateVideoPath(String id, String videoPath) {

    }

    //上传文件到本地  未用 废弃
    @Override
    public void videoUpload(MultipartFile videoPath, String id) {
        //文件上传
        //1.上传文件的位置
        String realPath = request.getSession().getServletContext().getRealPath("/upload/photo");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdir();
        }

        //获取文件名   并上传到本地
        String originalFilename = videoPath.getOriginalFilename();//文件名
        //为名字拼接时间戳    避免名字重复
        String newName = new Date().getTime() + "-" + originalFilename;

//        上传文件到本地
        File uploadFile = new File(realPath, newName);
        try {
            videoPath.transferTo(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //点睛之笔
//        根据id修改文件名字
//        videoDAO.updateVideoPath(id, newName);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> videoPage(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();

//        计算开始页数    展示行数
        Integer start = (page - 1) * rows;
        Integer stop = page * rows;

        //计算总页数
        Integer counts = videoDAO.counts();
        Integer total = counts % rows == 0 ? counts / rows : counts / rows + 1;

        //计算以后添加到map
        List<Video> videos = videoDAO.videoPage(start, stop);
        map.put("rows", videos);
        map.put("page", page);
        map.put("total", total);
        map.put("records",counts );

        return map;
    }

    //文件上传到AliYun服务器
    @Override
    @AddLog("上传视频，且修改视频信息")
    public void videoUploadAliYun(MultipartFile videoPath, String id) {
        byte[] bytes = null;
        try {
            bytes = videoPath.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //获取文件名
        String filename = videoPath.getOriginalFilename();
        String newName = new Date().getTime() + "-" + filename;

        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4GFTYCcFpF9SdK81kLut";
        String accessKeySecret = "72gM5CnrkhbGiJrzYajyKLlvcmV6TA";
        String bucketName = "yingx-lzh";  //存储空间名  yingx-2005
        String videoName = "cover/" + newName;  //保存的文件名   1.MP4  aaa.mp4

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传Byte数组。
        ossClient.putObject(bucketName, videoName, new ByteArrayInputStream(bytes));

        // 关闭OSSClient。
        ossClient.shutdown();


        //截取视频第一帧 作为封面
        //截取文件名
        String[] split = newName.split("\\.");
        //拼接图片名
        String coverName="img/"+split[0]+".jpg";

        /*
         * 2.截取视频第一帧
         * 参数:
         *   bucketName:存储空间名
         *   videoName:视频名  文件夹
         *   coverName:封面名
         * */
//        AliyunOSSUtil.interceptVideoCover("ying-lzh",videoName ,coverName);
        // 创建OSSClient实例。
        ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 设置视频截帧操作。
        String style = "video/snapshot,t_1000,f_jpg,w_800,h_600";
        // 指定过期时间为10分钟。
        Date expiration = new Date(new Date().getTime() + 1000 * 60 * 10 );
        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName, videoName, HttpMethod.GET);
        req.setExpiration(expiration);
        req.setProcess(style);
        URL signedUrl = ossClient.generatePresignedUrl(req);
        System.out.println("截取到的地址============="+signedUrl);
        //关闭OSSClient。
        ossClient.shutdown();


        //将截取到的第一帧封面图片上传到文件云服务器中



//      uploadFileNetIO(bucketName,coverName,signedUrl.toString());


        // 创建OSSClient实例。
        ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传网络流。
        InputStream inputStream = null;
        try {
            inputStream = new URL(signedUrl.toString()).openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ossClient.putObject(bucketName, coverName, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();


//        bytes = signedUrl.toString().getBytes();
//
//        ossClient.putObject(bucketName, coverName, new ByteArrayInputStream(bytes));
        //上传封面图片

        //修改文件名字
        //点睛之笔
//        根据id修改文件名字
        String newUploadName = "http://yingx-lzh.oss-cn-beijing.aliyuncs.com/"+videoName;
        String newCoverName = "http://yingx-lzh.oss-cn-beijing.aliyuncs.com/"+ coverName;
        System.out.println("coverName================"+coverName);

        //参数1 视频名字    参数2 封面图片名字
        videoDAO.updateVideoPath(id, newUploadName,newCoverName);
    }




    @AddLog("删除视频")
    @Override
    public void deleteVideo(String id) {
        Video video = videoDAO.queryOne(id);
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-beijing.aliyuncs.com/";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI4GFTYCcFpF9SdK81kLut";
        String accessKeySecret = "72gM5CnrkhbGiJrzYajyKLlvcmV6TA";
        String bucketName = "yingx-lzh";

        String videoPath = video.getVideoPath().replace("http://yingx-lzh.oss-cn-beijing.aliyuncs.com/", "");


        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
        String coverPath = video.getCoverPath().replace("http://yingx-lzh.oss-cn-beijing.aliyuncs.com/", "");
        ossClient.deleteObject(bucketName, videoPath);
        //删除封面
        ossClient.deleteObject(bucketName, coverPath);

        // 关闭OSSClient。
        ossClient.shutdown();

        videoDAO.deleteVideo(id);
    }


    //前台展示视频信息


    @RedisCache
    @Override
    public List<VideoPO> queryByReleaseTime() {
        List<VideoPO> videoPOS = videoDAO.queryByReleaseTime();
        for (VideoPO videoPO : videoPOS) {
            //获取视频id
            String id = videoPO.getId();
            //根据id去获取点赞数     因为未建立点赞表 同时想将 分数存储到redis中  方便进行运算
            videoPO.setLikeCount(10);
        }
        return videoPOS;
    }

    @RedisCache
    @Override
    public List<VideoPO> queryByLikeVideoName(String content) {

        List<VideoPO> videoPOS = videoDAO.queryByLikeVideoName(content);

        for (VideoPO videoPO : videoPOS) {
            String id = videoPO.getId();
            videoPO.setLikeCount(10);
        }

        return videoPOS;
    }
}
