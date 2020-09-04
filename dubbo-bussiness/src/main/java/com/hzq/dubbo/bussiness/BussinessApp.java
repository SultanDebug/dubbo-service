package com.hzq.dubbo.bussiness;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

import static com.hzq.dubbo.constants.CommonConstants.TRACEID;

@SpringBootApplication(scanBasePackages = "com.hzq.*")
@EnableDubbo(scanBasePackages = "com.hzq.*")
@MapperScans( value = {
        @MapperScan("com.hzq.dubbo.*.mapper")
})
public class BussinessApp
{
    public static void main( String[] args )
    {
        String traceId = "Start-"+ UUID.randomUUID().toString().replace("-", "");
        MDC.put(TRACEID, traceId);
        SpringApplication.run(BussinessApp.class,args);
    }
}
