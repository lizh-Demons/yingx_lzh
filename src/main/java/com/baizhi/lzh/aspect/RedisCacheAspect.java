package com.baizhi.lzh.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import sun.misc.Cache;

import java.util.Set;

@Configuration
@Aspect
public class RedisCacheAspect {


    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    //环绕通知  添加 使用缓存
//    @Around("@annotation(com.baizhi.lzh.annotation.RedisCache)")
    public Object addCache(ProceedingJoinPoint proceedingJoinPoint) {

        System.out.println("=======进入环绕通知========");
//        使用string 类型  存数据到缓存redis数据库中
//         key value
//           key  : 类名+方法名+参数名   value  查询到的数据
        //序列化解乱码
        StringRedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        stringRedisTemplate.setHashKeySerializer(redisSerializer);


        //使用可变长字符串  拼接key
        StringBuilder sb = new StringBuilder();

        //首先获取类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        sb.append(className);

        //获取方法名
        String methodName = proceedingJoinPoint.getSignature().getName();
        //将参数拼接到sb中
        sb.append(methodName);
        //获取方法中的参数
        Object[] args = proceedingJoinPoint.getArgs();
        //遍历数据  获取所有参数
        for (Object arg : args) {
            //将参数拼接到sb上
            sb.append(arg);
        }


        String key = sb.toString();//将sb转换成string类型

        //判断key是否存在
        Boolean aBoolean = redisTemplate.hasKey(key);
        //创建redis string 类型对象
        ValueOperations valueOperations = redisTemplate.opsForValue();

        Object result = null;
        //判断key是否存在
        if (aBoolean) {
            System.out.println("aBoolean==================="+aBoolean);
            //存在则在redis数据库中拿数据
            result = valueOperations.get(key);
            System.out.println(result);
        } else {
            try {
                //存在则返回数据
                result = proceedingJoinPoint.proceed();
                System.out.println("存在返回的数据为"+result);
                System.out.println(result);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            //否则则将数据添加到缓存中
            valueOperations.set(key, result);
        }

        return result;
    }


    //清除缓存   执行任意增删改方法之后删除redis数据库中的内容
//    @After("@annotation(com.baizhi.lzh.annotation.DelCache)")
    public void deleteRedis(JoinPoint joinPoint) {
        System.out.println("===============进入后置通知==============");

        //首先获取类的全限定名
        String className = joinPoint.getTarget().getClass().getName();

        //获取所有的key
        Set<String> keys = stringRedisTemplate.keys("*");
        //遍历所有key
        for (String key : keys) {

            //看看都有哪些key
            if (key.startsWith(className)) {
                redisTemplate.delete(keys);
            }

        }

    }
}
