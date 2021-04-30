
package com.hzq.dubbo.config.fastjson;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2021/4/29 16:13
 */
//@Configuration
@Slf4j
public class FastjsonMvcConfigure implements WebMvcConfigurer {
    @Autowired
    private FastJsonSerialize serialize;

    @Autowired
    private StringValueFilter valueFilter;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("fastjson mvc 配置加载");
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(//    输出key是包含双引号
//                SerializerFeature.QuoteFieldNames,
                //    是否输出为null的字段,若为null 则显示该字段
//                SerializerFeature.WriteMapNullValue,
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

        config.setSerializeFilters(valueFilter);

        converter.setFastJsonConfig(config);

        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        converter.setSupportedMediaTypes(fastMediaTypes);

        //4、将convert添加到converters中
        converters.add(0,converter);
    }
}
