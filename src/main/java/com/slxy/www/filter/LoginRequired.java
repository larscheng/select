package com.slxy.www.filter;

import java.lang.annotation.*;

/**
 * @author zhengql
 * @description 自定义注解拦截器
 * @className LoginRequired
 * @create 2018年04月02日  15:00
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface LoginRequired {
    String value() default "";
}

