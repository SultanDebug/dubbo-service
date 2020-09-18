/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.enableautoconfig;

import com.hzq.dubbo.config.HzqProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/9/18 16:43
 */
@Configuration
/*
* onBean 因为config加载无序  会导致加载失败
* */
//@ConditionalOnBean(HzqProperties.class)
@EnableConfigurationProperties(HzqProperties.class)
@ConditionalOnProperty(prefix = "hzq",value = "enable",matchIfMissing = true)
@Slf4j
public class DirectAutoConfiguration {
    @Autowired
    private HzqProperties properties;

    @Bean
    public DirectService getService(){
        log.info("加载direct-service");
        return new DirectService(properties);
    }
}
