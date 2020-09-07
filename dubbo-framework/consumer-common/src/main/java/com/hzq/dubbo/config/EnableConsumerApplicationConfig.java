
package com.hzq.dubbo.config;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/9/4 16:46
 */
@SpringBootApplication(scanBasePackages = "com.hzq.*")
@EnableDubbo(scanBasePackages = "com.hzq.*")
@MapperScans( value = {
        @MapperScan("com.hzq.dubbo.*.mapper")
})
public class EnableConsumerApplicationConfig {
    private static final Logger log = LoggerFactory.getLogger(EnableConsumerApplicationConfig.class);

    public EnableConsumerApplicationConfig() {
        log.info("启动初始化配置......");
    }
}
