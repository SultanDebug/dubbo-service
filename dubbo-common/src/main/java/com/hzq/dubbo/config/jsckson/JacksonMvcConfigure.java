
package com.hzq.dubbo.config.jsckson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2021/4/29 16:13
 */
@Configuration
@Slf4j
public class JacksonMvcConfigure implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("jackson mvc 配置加载");
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper mapper = new MyJacksonMapper();

        mapper.setDefaultPropertyInclusion(
                JsonInclude.Value.construct(JsonInclude.Include.ALWAYS, JsonInclude.Include.NON_NULL));
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        filterProvider.addFilter("myJacksonFilter",new MyJacksonFilter());
        mapper.setFilterProvider(filterProvider);

        converter.setObjectMapper(mapper);

        converters.add(converter);
        converters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));

        //4、将convert添加到converters中
        converters.add(0,converter);
    }
}
