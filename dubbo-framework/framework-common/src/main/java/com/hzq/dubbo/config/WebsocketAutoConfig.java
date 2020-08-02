
package com.hzq.dubbo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * ws配置
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/7/3 11:52
 */
@Configuration
public class WebsocketAutoConfig {
    @Bean
    public ServerEndpointExporter get(){
        return new ServerEndpointExporter();
    }
}
