/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.aop;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/5/28 16:16
 */
@Aspect
@Component
@Slf4j
public class LogAopDubbo {

    @Around("execution(public * com.hzq.*.api..*(..))")
    public Object process(ProceedingJoinPoint point) throws Throwable {
        String className = point.getTarget().getClass().getSimpleName()+"."+point.getSignature().getName();

        try {
            /**
             * 日志打印
             */
            log.info("执行{}，入参为：{}",className, JSONArray.toJSONString(paramsList(point)));

            /**
             * threadlocal设置
             */
            /*RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            HttpServletRequest request = sra.getRequest();*/

            String user = RpcContext.getContext().getAttachment("user");

            UserInfo.setUser(user);

            Object result = point.proceed();

            log.info("执行{}，出参为：{}",className, JSON.toJSONString(result));

            UserInfo.removeUser();
            return result;
        }catch (Exception e){
            e.printStackTrace();
            UserInfo.removeUser();
        }

        return null;
    }

    private List<Object> paramsList(ProceedingJoinPoint point){
        List<Object> params = new ArrayList<>();

        Object[] objects = point.getArgs();

        for (Object object : objects) {
            if(! (object instanceof HttpServletRequest) && ! (object instanceof HttpServletResponse)){
                params.add(object);
            }
        }

        return params;

    }
}
