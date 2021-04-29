/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.hzq.dubbo.service.FastJsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 无效
 * 原因：HttpMessageConverters 顺序默认jackson没有去除  而且排序在fastjson前面
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2021/4/28 17:45
 */
//@Configuration
public class FastJsonAutoConfig {
//    @Autowired
    private FastJsonSerialize serialize;

//    @Bean
    public HttpMessageConverters get(){
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(//    输出key是包含双引号
//                SerializerFeature.QuoteFieldNames,
                //    是否输出为null的字段,若为null 则显示该字段
                SerializerFeature.WriteMapNullValue,
                //    数值字段如果为null，则输出为0
//                SerializerFeature.WriteNullNumberAsZero,
                //     List字段如果为null,输出为[],而非null
//                SerializerFeature.WriteNullListAsEmpty,
                //    字符类型字段如果为null,输出为"",而非null
//                SerializerFeature.WriteNullStringAsEmpty,
                //    Boolean字段如果为null,输出为false,而非null
//                SerializerFeature.WriteNullBooleanAsFalse,
                //    Date的日期转换器
                SerializerFeature.WriteDateUseDateFormat,
                //    循环引用
                SerializerFeature.DisableCircularReferenceDetect
        );

        config.getSerializeConfig().put(String.class,serialize);

        converter.setFastJsonConfig(config);

        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        converter.setSupportedMediaTypes(fastMediaTypes);

        //4、将convert添加到converters中
        HttpMessageConverter<?> c = converter;

        return new HttpMessageConverters(c);
    }
}
