package com.baizhi.lzh.app;


import com.baizhi.lzh.commom.CommonResult;
import com.baizhi.lzh.entity.Category;
import com.baizhi.lzh.po.VideoPO;
import com.baizhi.lzh.service.CategoryService;
import com.baizhi.lzh.service.VideoService;
import com.baizhi.lzh.util.AliYunUtil;
import com.baizhi.lzh.util.ImageCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

//因为打给前台的都是json的字符串  所以
@RestController
@RequestMapping("app")
public class AppController {

    //    依赖video业务
    @Autowired
    private VideoService videoService;

    @Autowired
    private CategoryService categoryService;


    @RequestMapping("getPhoneCode")
    public Object getPhoneCode(String phone) {

//        使用验证码工具类生成工具类
        String securityCode = ImageCodeUtil.getSecurityCode();
        System.out.println("手机验证码" + securityCode);
        String message = null;
        try {
            message = AliYunUtil.sendPhoneMsg(phone, securityCode);
            return new CommonResult().success(message, phone);
        } catch (Exception e) {
            return new CommonResult().failed(message);
        }
    }


    //前台首页展示    并且根据时间排序
    @RequestMapping("queryByReleaseTime")
    public Object queryByReleaseTime() {

        try {
            List<VideoPO> videoPOS = videoService.queryByReleaseTime();
            System.out.println("进来了没");
            return new CommonResult().success(videoPOS);
        } catch (Exception e) {
            return new CommonResult().failed();
        }
    }


    @RequestMapping("queryByLikeVideoName")
    public Object queryByLikeVideoName(String content) {

        try {
            List<VideoPO> videoPOS = videoService.queryByLikeVideoName(content);
            return new CommonResult().success(videoPOS);
        } catch (Exception e) {
            return new CommonResult().failed();
        }
    }



    @RequestMapping("queryAllCategory")
    public Object queryAllCategory(){
        try {
            List<Category> categories = categoryService.queryAllCategory();
            return new CommonResult().success(categories);
        } catch (Exception e) {
            return new CommonResult().failed();
        }
    }




}
