package com.hzq.dubbo.bussiness.proxy;

import java.lang.annotation.*;

/**
 * 模拟transaction注解
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/7/24 16:13
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InTransfer {

}
