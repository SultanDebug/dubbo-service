/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/5/28 17:46
 */
@Activate(group={ Constants.PROVIDER, Constants.CONSUMER })
@Slf4j
public class RPCFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        //todo 拦截逻辑
        String method = invocation.getMethodName();
        String name = invoker.getInterface().getName();
        Object[] args = invocation.getArguments();
        String ageString = JSON.toJSONString(args);
        log.info("执行拦截逻辑:{},{},{}",method,name,ageString);

        RpcContext.getContext().setAttachment("user","rpcFilter");

        return invoker.invoke(invocation);
    }
}
