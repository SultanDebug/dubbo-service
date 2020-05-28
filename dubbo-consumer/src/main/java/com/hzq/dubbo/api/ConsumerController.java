package com.hzq.dubbo.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSONObject;
import com.hzq.dubbo.aop.UserInfo;
import com.hzq.dubbo.provider.ProviderInterface;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020/5/23
 */
@RestController
public class ConsumerController {
    /**
     * check 没人为true 会检测provider健康状态  要求provider必须先启动  要求启动顺序
     * url 指定调用地址  方便测试  此时check参数失效
     *      会报错但不是启动失败 启动 provider 消费端会重连服务提供方
     */
    /**
     * check属性检查提供方状态，同时会导致在消费方启动时提供方必须启动，否则调用报错
     * 指定被调用方地址，不走负载
     * @Reference(url = "dubbo://localhost:20005")
     */
    @Reference(check = false/*,url = "dubbo://192.168.50.154:21002"*/)
    private ProviderInterface providerInterface;

    @GetMapping("/remote")
    public String remote(String para){
        System.out.println(UserInfo.getUser());
//        RpcContext.getContext().setAttachment("user","sultan");
        return JSONObject.toJSONString(providerInterface.remote(para));
    }
}
