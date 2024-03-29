package com.hzq.dubbo.filter;

import com.hzq.dubbo.aop.LogTrace;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.registry.Constants;
import org.apache.dubbo.rpc.*;
import com.alibaba.fastjson.JSON;
import com.hzq.dubbo.aop.UserInfo;
import lombok.extern.slf4j.Slf4j;

import static com.hzq.dubbo.constants.CommonConstants.TOKEN;
import static com.hzq.dubbo.constants.CommonConstants.TRACEID;

/**
 * rpc客户端过滤器
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/5/28 17:46
 */
@Activate(group={ /*Constants.PROVIDER, */Constants.CONSUMER_PROTOCOL },order = 1)
@Slf4j
public class RPCFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        log.info("1号拦截器开始执行");
        //todo 拦截逻辑
        String method = invocation.getMethodName();
        String name = invoker.getInterface().getName();
        Object[] args = invocation.getArguments();
        String ageString = JSON.toJSONString(args);
        log.info("执行拦截逻辑:{},{},{}",method,name,ageString);

        /**
         * path,group,version,dubbo,token,timeout几个key有特殊处理，如在invoke时会清空、重设等
         * 位置：ContextFilter 透传参数key值数据清理
         */
        RpcContext.getContext().setAttachment(TOKEN, UserInfo.getUser());
        RpcContext.getContext().setAttachment(TRACEID, LogTrace.getTraceid());

        log.info("1号拦截器结束执行");
        return invoker.invoke(invocation);
    }
}
