package com.baizhi.lzh.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.KeyBoundCursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.security.Key;

@Configuration
@Aspect
public class CacheHashRedis {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;


    //环绕通知
    @Around("@annotation(com.baizhi.lzh.annotation.RedisCache)")
    public Object addCache(ProceedingJoinPoint proceedingJoinPoint){

        //序列化乱码解决
        StringRedisSerializer redisSerializer = new StringRedisSerializer();
        //大key
        redisTemplate.setKeySerializer(redisSerializer);
        //hash key
        redisTemplate.setHashKeySerializer(redisSerializer);

        //获取类名作为大key
        String className = proceedingJoinPoint.getTarget().getClass().getName();

        //使用可变长字符串 拼接方法名作为小key
        StringBuilder sb = new StringBuilder();
//        获取方法名
        String methodName = proceedingJoinPoint.getSignature().getName();

        sb.append(methodName);
        //获取参数  参数有多个 为数组
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            //拼接为大key
            sb.append(arg);
        }
        //使用序列话对象获取key值判断  redis数据库中是否存在数据
        //小key
        String key = sb.toString();

        //获取hash类型对象
        HashOperations hashOperations  = redisTemplate.opsForHash();

        Boolean aBoolean = hashOperations.hasKey(className, key);
        Object result = null;
        if(aBoolean){
            //为true则获取
            System.out.println(aBoolean);
            result = hashOperations.get(className, key);
        }else{
            try {
                //方法执行完   有数据则返回
                 result = proceedingJoinPoint.proceed();
                System.out.println("缓存存在返回的数据为"+result);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            //没有则   添加到redis数据库中
            hashOperations.put(className,key,result);
        }

        return result;
    }

    @After("@annotation(com.baizhi.lzh.annotation.DelCache)")
    public void delCache(JoinPoint joinPoint){
        System.out.println("===========进入后置通知 删除redis缓存==========");
        //获取类的全限定名 作为大key  以供删除
        String classname = joinPoint.getTarget().getClass().getName();
        Boolean delete = redisTemplate.delete(classname);
        System.out.println("删除redis数据库数据状态======="+delete);
    }

}
