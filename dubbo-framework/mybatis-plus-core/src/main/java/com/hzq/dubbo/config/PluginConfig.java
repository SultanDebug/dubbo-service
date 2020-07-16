
package com.hzq.dubbo.config;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.hzq.dubbo.plugins.QueryPlugin;
import com.hzq.dubbo.plugins.UpdatePlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注册插件
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/7/13 11:51
 */
@Configuration
public class PluginConfig {
    @Bean
    ConfigurationCustomizer mybatisConfigurationCustomizer() {
        return new ConfigurationCustomizer() {
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                configuration.addInterceptor(new QueryPlugin());
                configuration.addInterceptor(new UpdatePlugin());
            }
        };
    }
}
