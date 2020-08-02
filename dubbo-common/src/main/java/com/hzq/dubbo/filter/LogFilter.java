
package com.hzq.dubbo.filter;

import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.UUID;

/**
 * mvc过滤器 日志生成日志id
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/6/18 10:04
 */
//@WebFilter(filterName = "LogFilter", urlPatterns = "/*")
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("web过滤器");
        UUID uuid = UUID.randomUUID();
        MDC.put("X-B3-TraceId", uuid.toString().replace("-",""));
        try {
            filterChain.doFilter(servletRequest,servletResponse);
        }finally {
            MDC.remove("X-B3-TraceId");//保证一次请求一个唯一标识
        }
    }

    @Override
    public void destroy() {

    }
}
