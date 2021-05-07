
package com.hzq.dubbo.config.jsckson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2021/4/30 11:38
 */
@Slf4j
public class MyJacksonFilter extends SimpleBeanPropertyFilter {
    @Override
    protected boolean include(BeanPropertyWriter writer) {
        log.info("BeanPropertyWriter 过滤");
        return filter(writer.getName(), writer.getType());
    }

    @Override
    protected boolean include(PropertyWriter writer) {
        log.info("PropertyWriter 过滤");
        return filter(writer.getName(), writer.getType());
    }

    private boolean filter(String fieldName, JavaType fieldType) {
//			System.out.println(fieldName + " " + fieldType.getTypeName() + " " + fieldType.getRawClass());
        // 排除以n开头的字段，以及int类型的字段
        if (fieldName.startsWith("n") || fieldType.getRawClass() == int.class) {
            return false;
        }
        return true;
    }

}
