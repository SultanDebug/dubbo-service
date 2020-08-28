
package com.hzq.dubbo.awareselector;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/8/17 14:37
 */
@Component
public class MyAware implements ImportAware , EnvironmentAware , BeanNameAware {
    @Override
    public void setBeanName(String s) {
        System.out.println("注入名字："+s);
    }

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("注入环境："+environment);
    }

    @Override
    public void setImportMetadata(AnnotationMetadata annotationMetadata) {
        System.out.println("注入注解元数据："+annotationMetadata);
    }
}
