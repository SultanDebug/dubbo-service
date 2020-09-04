package com.hzq.dubbo.aop;

import org.apache.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.hzq.dubbo.constants.CommonConstants.*;

/**
 * rpc切面
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/5/28 16:16
 */
@Aspect
@Component
@Slf4j
public class LogAopDubbo {

    @Around("execution(public * com.hzq.*..api..*(..))")
    public Object process(ProceedingJoinPoint point) throws Throwable {
        String className = point.getTarget().getClass().getSimpleName()+"."+point.getSignature().getName();
        String traceId = RpcContext.getContext().getAttachment(TRACEID);
        String spanId = UUID.randomUUID().toString().replace("-", "");
        MDC.put(TRACEID, traceId);
        MDC.put(SPANID, spanId);

        LogTrace.setTraceid(traceId);
        try {
            /**
             * 日志打印
             */
            log.info("执行{}，入参为：{}",className, JSONArray.toJSONString(paramsList(point)));

            /**
             * rpc透传参数的 threadlocal设置
             */
            String token = RpcContext.getContext().getAttachment(TOKEN);

            UserInfo.setUser(token);

            Object result = point.proceed();

            log.info("执行{}，出参为：{}",className, JSON.toJSONString(result));

            log.info("userinfo信息:{}", JSONObject.toJSONString(UserInfo.getUserInfo()));

            //调用完清除线程变量
//            UserInfo.removeUser();
//            RpcContext.getContext().clearAttachments();
            return result;
        }catch (Exception e){
            e.printStackTrace();
            UserInfo.removeUser();
        }finally {
            MDC.remove(SPANID);
        }

        return null;
    }

    /**
     * 参数序列化
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/6/4 10:34
    */
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
