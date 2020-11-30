package com.baizhi.lzh.controller;


import com.baizhi.lzh.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("feedback")
public class FeedbackController {

    //依赖于业务
    @Autowired
    private FeedbackService feedbackService;

    //分页查询
    @RequestMapping("feedbackPage")
    @ResponseBody
    public Map<String,Object> feedbackPage(Integer page,Integer rows){
        Map<String, Object> map = feedbackService.feedbackPage(page, rows);
        return map;
    }


}
