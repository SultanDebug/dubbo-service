package com.hzq.dubbo.annotation;

import com.hzq.dubbo.interfaces.ProcessDefault;
import com.hzq.dubbo.interfaces.ProcessInterface;

import java.lang.annotation.*;

/**
 * 验证dto字段值修改
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/10/21 10:53
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommonProcess {
    Class<? extends ProcessInterface> clazz() default ProcessDefault.class;

    String beanName() default "defaultProcess";
}
