package com.baizhi.lzh.aspect;


import com.baizhi.lzh.annotation.AddLog;
import com.baizhi.lzh.dao.LogDAO;
import com.baizhi.lzh.entity.Admin;
import com.baizhi.lzh.entity.Log;
import com.baizhi.lzh.service.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

@Configuration
@Aspect
// 注解1 多用于定义配置类
// 注解2  指定该类为配置类
public class LogAspect {

    //依赖于session
    @Resource
    HttpServletRequest request;
    //依赖于dao
    @Autowired
    private LogService logService;

    @Around("@annotation(com.baizhi.lzh.annotation.AddLog)")
    public Object addLog(ProceedingJoinPoint proceedingJoinPoint){


//        使用环绕通知  获取管理员操作日志
//        首先获取用户信息

//        System.out.println(admin);

        //获取切到的方法名
        String methodName = proceedingJoinPoint.getSignature().getName();


        //获取方法
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();

        //通过方法去获取注解
        AddLog addLog = method.getAnnotation(AddLog.class);
        //获取注解对应的属性值  用户操作
        String value = addLog.value();

        //方法+操作
        String event = methodName+" "+value;
        System.out.println(event);

        //环绕通知 环执行 完以后  放行
        //操作状态
        String message = null;
        Object proceed = null;
        try {
            proceed = proceedingJoinPoint.proceed();
            message = "success";
        } catch (Throwable throwable) {
            message = "error";
        }
        Admin admin = (Admin) request.getSession().getAttribute("admin");

        //new一个日志对象
        Log log = new Log();
        log.setId(UUID.randomUUID().toString());//Id
        log.setName(admin.getUsername());
        log.setTimes(new Date());
        log.setEvent(event);
        log.setStatus(message);

        //添加入库
        logService.addLog(log);

        //执行完放行  要返回执行的结果
        return proceed;
    }

}
