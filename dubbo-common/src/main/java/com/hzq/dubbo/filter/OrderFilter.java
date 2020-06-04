package com.hzq.dubbo.filter;

import org.apache.dubbo.common.Constants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import com.alibaba.fastjson.JSON;
import com.hzq.dubbo.aop.UserInfo;
import com.hzq.dubbo.constants.CommonConstants;
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

        //todo 拦截逻辑 此处也可接收rpc透传参数存threadlocal
        /*log.info("Filter获取测试参数user:{}",RpcContext.getContext().getAttachment(CommonConstants.TOKEN));
        String token = RpcContext.getContext().getAttachment(CommonConstants.TOKEN);
        log.info("获取token：{}",token);
        UserInfo.setUser(token);*/

        log.info("2号拦截器结束执行");
        return invoker.invoke(invocation);
    }
}
