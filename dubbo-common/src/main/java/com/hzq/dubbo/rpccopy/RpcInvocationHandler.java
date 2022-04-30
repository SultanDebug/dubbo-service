package com.hzq.dubbo.rpccopy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: Huangzq
 * @Date: 2022/4/30 17:29
 */
public class RpcInvocationHandler implements InvocationHandler {

    private String url;
    private String port;

    public RpcInvocationHandler(String url, String port) {
        this.url = url;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return RpcCall.call(url,port,method,args);
    }
}
