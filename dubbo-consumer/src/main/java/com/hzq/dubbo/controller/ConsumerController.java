package com.hzq.dubbo.controller;

import com.alibaba.fastjson.JSONObject;
import com.hzq.dubbo.User;
import com.hzq.dubbo.aop.ResultResponse;
import com.hzq.dubbo.aop.UserInfo;
import com.hzq.dubbo.provider.ProviderInterface;
import com.hzq.dubbo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020/5/23
 */
@RestController
@Slf4j
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
     *
     * 大智不群  大善无帮  重剑不锋  大巧不工
     *
     * sometimes it lasts in love , but sometimes it hurt instead
     */
    @Reference(check = false/*,url = "dubbo://192.168.50.154:21002"*/,loadbalance = "consistenthash")
    private ProviderInterface providerInterface;


    @Autowired
    private UserService userService;


    /**
     * rpc测试
     * @param para
     * @return
     */
    @GetMapping("/remote")
    public ResultResponse<String> remote(String para){
        log.info("获取token解析信息："+JSONObject.toJSONString(UserInfo.getUserInfo()));
//        RpcContext.getContext().setAttachment("token", UserInfo.getUser());
//        RpcContext.getContext().setAttachment("user", "sultan");
        return ResultResponse.success(providerInterface.remote(para).getData());
    }



    /**
     * mymybatis测试   *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/8/2 11:10
    */
    @GetMapping("/getUser")
    public ResultResponse<User> getUser(Integer id){
        return ResultResponse.success(userService.getById(id));
    }



}
