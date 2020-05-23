package com.hzq.dubbo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hzq.dubbo.provider.ProviderInterface;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
     * url 指定调用地址  方便测试  此时check参数失效  启动 provider 消费端会重连服务提供方
     */
    @Reference(check = false,url = "dubbo://192.168.50.154:21002")
    private ProviderInterface providerInterface;

    @GetMapping("/remote")
    public String remote(String para){
        return providerInterface.remote(para);
    }
}
