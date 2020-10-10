
package com.hzq.dubbo.gateway;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;

import static com.hzq.dubbo.constants.CommonConstants.TRACEID;

/**
 * 网关层
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/9/23 10:34
 */
@Slf4j
@Order
@Component
public class GatewayFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("网关初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            String traceId = UUID.randomUUID().toString().replace("-", "");
            MDC.put(TRACEID, traceId);
            log.info("网关操作");
            filterChain.doFilter(servletRequest,servletResponse);
            log.info("网关操作结束");
        }finally {
            MDC.remove(TRACEID);
        }

    }

    @Override
    public void destroy() {
        log.info("网关销毁");
    }
}
