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
@Activate(group={ Constants.PROVIDER/*, Constants.CONSUMER*/ },order = 10)
@Slf4j
public class OrderFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        log.info("2号拦截器开始执行");
        //todo 拦截逻辑
        log.info("2号拦截器结束执行");
        return invoker.invoke(invocation);
    }
}
