package com.hzq.dubbo.importselector;

import com.hzq.dubbo.config.HzqProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 注解形式
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
@EnableConfigurationProperties(HzqProperties.class)
public @interface MyImportSelector {
}
