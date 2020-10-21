package com.hzq.dubbo.annotation;

import java.lang.annotation.*;

/**
 * 权限点  仅限方法  拼接parentkey
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/10/20 16:52
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Auth {
    String code();
    String name();
}
