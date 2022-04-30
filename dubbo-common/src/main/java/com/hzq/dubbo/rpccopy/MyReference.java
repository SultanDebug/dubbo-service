package com.hzq.dubbo.rpccopy;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.*;

/**
 * @Author: Huangzq
 * @Date: 2022/4/30 17:15
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Autowired(required = false)
public @interface MyReference {
    String host() default "localhost";
    String port() default "8080";
}
