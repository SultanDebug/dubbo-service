/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.config;

import com.alibaba.fastjson.serializer.ValueFilter;
import com.hzq.dubbo.annotation.CommonProcess;
import com.hzq.dubbo.context.ApplicationContextUtils;
import com.hzq.dubbo.interfaces.ProcessInterface;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2021/4/29 16:52
 */
@Component
public class StringValueFilter implements ValueFilter {
    @Override
    public Object process(Object object, String name, Object value) {

        try {
            Field field = object.getClass().getDeclaredField(name);
            CommonProcess annotation = field.getAnnotation(CommonProcess.class);
            if (annotation!=null){
                ProcessInterface bean = ApplicationContextUtils.getApplicationContext().getBean(annotation.beanName(),ProcessInterface.class);
                value = bean.run((String) value);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return value;
    }
}
