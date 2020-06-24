package com.hzq.dubbo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.UUID;

import static com.hzq.dubbo.constants.CommonConstants.TRACEID;

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
        String traceId = "Start-"+ UUID.randomUUID().toString().replace("-", "");
        MDC.put(TRACEID, traceId);
        SpringApplication.run(DubboProviderApp.class,args);
//        new SpringApplicationBuilder(DubboProviderApp.class)
//                .web(WebApplicationType.NONE)
//                .run(args);
    }
}
