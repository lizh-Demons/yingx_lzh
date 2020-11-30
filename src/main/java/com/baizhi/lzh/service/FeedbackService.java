package com.baizhi.lzh.service;

import java.util.Map;

public interface FeedbackService {
    Map<String,Object> feedbackPage(Integer page, Integer rows);
}
