
package com.hzq.dubbo.importselector;

import com.hzq.dubbo.service.ImportSelectorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

/**
 * selector形式
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/9/18 17:54
 */
@Slf4j
public class MyEnableConfigInportSelector implements ImportSelector, BeanClassLoaderAware, EnvironmentAware, BeanFactoryAware {
    private Environment environment;
    private ClassLoader classLoader;
    private BeanFactory beanFactory;
    /**
     * importselector形式装载bean
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/10/21 11:55
    */
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        Boolean property = this.environment.getProperty("hzq.enable", Boolean.class, Boolean.TRUE);
        log.info("加载 import selector : {}",property);
        if(!property){
            return new String[0];
        }else{
            return new String[]{ImportSelectorService.class.getName()};
        }
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
