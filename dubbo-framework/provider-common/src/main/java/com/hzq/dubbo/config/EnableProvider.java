
package com.hzq.dubbo.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/9/4 16:45
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({EnableProviderApplicationConfig.class})
public @interface EnableProvider {
}
