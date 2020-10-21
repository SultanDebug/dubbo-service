package com.hzq.dubbo.annotation;

import java.lang.annotation.*;

/**
 * 权限点 仅限类  拼接auth
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/10/20 16:52
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ParentKey {
    String code() ;
}
