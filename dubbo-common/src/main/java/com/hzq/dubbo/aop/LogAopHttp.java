package com.hzq.dubbo.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.hzq.dubbo.jwt.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static com.hzq.dubbo.constants.CommonConstants.AUTH;
import static com.hzq.dubbo.constants.CommonConstants.WHITE;

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
public class LogAopHttp {

    @Around("execution(public * com.hzq.*.controller..*(..))")
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
            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            HttpServletRequest request = sra.getRequest();

            String white = request.getHeader(WHITE);

            String token = request.getHeader(AUTH);

            log.info("获取white参数：{}，token：{}",white, token);

            if(!StringUtils.isEmpty(white) && white.equals("0")){
                if(StringUtils.isEmpty(token) || !JwtUtils.checkToken(token)){
                    return ResultResponse.fail("500","token参数缺失或者token校验失败");
                }
                UserInfo.setUser(token);
            }else{
                UserInfo.setAdminUser();
            }

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
