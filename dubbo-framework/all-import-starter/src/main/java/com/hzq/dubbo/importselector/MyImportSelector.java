package com.hzq.dubbo.importselector;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/9/18 17:55
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(MyEnableConfigInportSelector.class)
public @interface MyImportSelector {
}
