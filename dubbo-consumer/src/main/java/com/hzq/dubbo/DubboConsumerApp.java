package com.hzq.dubbo;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = "com.hzq.*")
@EnableDubbo(scanBasePackages = "com.hzq.*")
public class DubboConsumerApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(DubboConsumerApp.class,args);
    }
}
