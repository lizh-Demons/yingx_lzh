package com.baizhi.lzh.dao;

import com.baizhi.lzh.entity.Feedback;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FeedbackDAO {
    List<Feedback> feedbackPage(@Param("start") Integer start, @Param("stop") Integer stop);

    //查数量
    Integer queryTotals();
}
