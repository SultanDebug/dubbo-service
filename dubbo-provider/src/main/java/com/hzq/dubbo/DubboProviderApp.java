package com.hzq.dubbo;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = "com.hzq.*")
@EnableDubbo(scanBasePackages = "com.hzq.*")
public class DubboProviderApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(DubboProviderApp.class,args);
//        new SpringApplicationBuilder(DubboProviderApp.class)
//                .web(WebApplicationType.NONE)
//                .run(args);
    }
}
