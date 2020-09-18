/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.importselector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/9/18 17:54
 */
public class MyEnableConfigInportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        /*if (!isEnabled()) {
            return new String[0];
        }
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(
                metadata.getAnnotationAttributes(this.annotationClass.getName(), true));

        Assert.notNull(attributes, "No " + getSimpleName() + " attributes found. Is "
                + metadata.getClassName() + " annotated with @" + getSimpleName() + "?");

        // Find all possible auto configuration classes, filtering duplicates
        // 调用SpringFactoriesLoader的loadFactoryNames去加载
        List<String> factories = new ArrayList<>(new LinkedHashSet<>(SpringFactoriesLoader
                .loadFactoryNames(this.annotationClass, this.beanClassLoader)));

        //省略了错误判断和多于一个的log

        return factories.toArray(new String[factories.size()]);*/
        return null;
    }
}
