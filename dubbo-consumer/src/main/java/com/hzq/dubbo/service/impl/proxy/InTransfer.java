package com.hzq.dubbo.service.impl.proxy;

import java.lang.annotation.*;

/**
 * 功能说明
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
