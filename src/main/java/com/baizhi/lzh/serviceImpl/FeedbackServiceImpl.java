package com.baizhi.lzh.serviceImpl;

import com.baizhi.lzh.annotation.RedisCache;
import com.baizhi.lzh.dao.FeedbackDAO;
import com.baizhi.lzh.entity.Feedback;
import com.baizhi.lzh.entity.Video;
import com.baizhi.lzh.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("feedbackService")
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackDAO feedbackDAO;

    @RedisCache
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> feedbackPage(Integer page, Integer rows) {

        Map<String, Object> map = new HashMap<>();

//        计算开始页数    展示行数
        Integer start = (page - 1) * rows;
        Integer stop = page * rows;

        //计算总页数
        Integer counts = feedbackDAO.queryTotals();
        Integer total = counts % rows == 0 ? counts / rows : counts / rows + 1;

        //计算以后添加到map
        List<Feedback> feedbacks = feedbackDAO.feedbackPage(start, stop);
        map.put("rows", feedbacks);
        map.put("page", page);
        map.put("total", total);
        map.put("records",counts );

        return map;
    }
}
