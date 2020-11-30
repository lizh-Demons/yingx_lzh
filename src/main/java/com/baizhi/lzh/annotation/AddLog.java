package com.baizhi.lzh.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//用在方法上
@Target({ElementType.METHOD})
//运行时生效
@Retention(RetentionPolicy.RUNTIME)
public @interface AddLog {

    String value() default "";

}
