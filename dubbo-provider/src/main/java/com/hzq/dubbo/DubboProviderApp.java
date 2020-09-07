package com.hzq.dubbo;

import com.hzq.dubbo.config.EnableProvider;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import java.util.UUID;
import static com.hzq.dubbo.constants.CommonConstants.TRACEID;

/**
 * Hello world!
 *
 */
@EnableProvider
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
