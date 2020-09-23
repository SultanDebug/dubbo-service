package com.hzq.dubbo.bussiness;

import com.hzq.dubbo.config.EnableComsumer;
import com.hzq.dubbo.importselector.MyImportSelector;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

import static com.hzq.dubbo.constants.CommonConstants.TRACEID;

@EnableComsumer
@MyImportSelector
public class BussinessApp
{
    public static void main( String[] args )
    {
        String traceId = "Start-"+ UUID.randomUUID().toString().replace("-", "");
        MDC.put(TRACEID, traceId);
        SpringApplication.run(BussinessApp.class,args);
    }
}
