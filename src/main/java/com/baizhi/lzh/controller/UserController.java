package com.baizhi.lzh.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baizhi.lzh.annotation.AddLog;
import com.baizhi.lzh.entity.Distribution;
import com.baizhi.lzh.entity.Statistics;
import com.baizhi.lzh.entity.User;
import com.baizhi.lzh.service.UserService;
import com.baizhi.lzh.util.AliYunUtil;
import com.baizhi.lzh.util.ImageCodeUtil;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.apache.poi.ss.usermodel.Workbook;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.Mergeable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {

    //依赖于业务
    @Autowired
    private UserService userService;


    @ResponseBody
    @RequestMapping("showPage")
    public Map<String, Object> showPage(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        List<User> users;
        Integer counts;
        Integer totals;
        //分页查询
        users = userService.showPage(page, rows);
        //查询数量
        counts = userService.queryTotals();
        //总页数有行数计算得来
        totals = counts % rows == 0 ? counts / rows : counts / rows + 1;

        map.put("page", page);
        map.put("records", counts);
        map.put("total", totals);
        map.put("rows", users);

        return map;
    }



    //修改用户状态
    @RequestMapping("updateStatus")
    @ResponseBody
    public Map<String,Object> updateStatus(String status, String id) {
        Map<String, Object> map = userService.updateStatus(status, id);
        return map;
    }


    //发送验证码
    @RequestMapping("sendPhoneMsg")
    @ResponseBody
    public Map<String, Object> sendPhoneMsg(String phone) {
        System.out.println("用户输入手机号" + phone);
        Map<String, Object> map = null;
        String message = null;
        try {
//            for(int i=1;i<6;i++){
                String securityCode = ImageCodeUtil.getSecurityCode();
                message = AliYunUtil.sendPhoneMsg(phone, securityCode);
                System.out.println("验证码为" + securityCode);
                System.out.println("手机验证码发送状态"+message);
//            }
            map = new HashMap<>();
            map.put("status", 200);
            map.put("message", message);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 201);
            map.put("message", message);
        }
        return map;
    }

//    @RequestMapping("getUserData")
//    @ResponseBody
//    public Map<String,Object> getUserData(){
//        Map<String, Object> userData = userService.getUserData();
//        return userData;
//    }


    @RequestMapping("export")
    @ResponseBody
    public Map<String,Object> export(){
        Map<String,Object> map = new HashMap<>();

        String message = null;
        List<User> users = userService.queryAllUser();

        ExportParams exportParams = new ExportParams("用户信息","用户登录信息");

        Workbook workbooks = ExcelExportUtil.exportBigExcel(exportParams, User.class, users);

        try {
            workbooks.write(new FileOutputStream(new File("D:\\UserExport.xls")));
            message = "success";
            map.put("message",message);
        } catch (IOException e) {
            e.printStackTrace();
            message = "error";
            map.put("message",message);
        }

        return map;
    }



    @RequestMapping("import")
    @ResponseBody
    public Map<String,Object> lead(){
        Map<String,Object> map = new HashMap<>();

        //导入设置的参数
        ImportParams params = new ImportParams();
        params.setTitleRows(1);  //标题所占行
        params.setHeadRows(1);   //表头所占行

        //导入
        List<User> users = null;
        String message = null;
        try {
            users = ExcelImportUtil.importExcel(new File("D:\\UserExport.xls"), User.class, params);
            message = "success";
            map.put("message",message);
        } catch (Exception e) {
            message = "error";
            map.put("message",message);
            e.printStackTrace();
        }

        users.forEach(user -> System.out.println(user));
        return map;
    }

    @RequestMapping("getChinaData")
    @ResponseBody
    public  List<Distribution> getChinaData(){
        List<Distribution> dList = userService.getDistribution();
        return  dList;
    }


    @RequestMapping("getUserData")
    @ResponseBody
    public Map<String,Object> getUserData(){
        Map<String, Object> map = userService.queryByMonth();
        return map;
    }
}
