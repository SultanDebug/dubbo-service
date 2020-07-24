
package com.hzq.dubbo.service.impl.proxy;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 事物模拟
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/7/24 16:11
 */
@Aspect
@Component
@Slf4j
public class InAop {
    @Around(value = "@annotation(in)")
    public void inProcess(ProceedingJoinPoint point,InTransfer in){
        log.info("开始代理");
        try {
            point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        log.info("结束代理");
    }
}
