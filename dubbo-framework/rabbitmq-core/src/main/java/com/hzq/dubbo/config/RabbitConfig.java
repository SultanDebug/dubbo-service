package com.hzq.dubbo.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * mq参数获取
 * @author Huangzq
 * @title: RabbitConfig
 * @projectName applications
 * @date 2019/12/13 15:11
 */
@Slf4j
@Component
@ConfigurationProperties(prefix = "spring.rabbitmq")
@Data
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RabbitConfig {
    private String host;

    private int port;

    private String username;

    private String password;
}
