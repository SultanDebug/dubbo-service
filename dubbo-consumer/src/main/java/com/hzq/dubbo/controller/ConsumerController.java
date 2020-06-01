package com.hzq.dubbo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSONObject;
import com.hzq.dubbo.aop.ResultResponse;
import com.hzq.dubbo.aop.UserInfo;
import com.hzq.dubbo.jwt.JwtUtils;
import com.hzq.dubbo.provider.ProviderInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

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
     */
    @Reference(check = false/*,url = "dubbo://192.168.50.154:21002"*/)
    private ProviderInterface providerInterface;

    @PostMapping("/login")
    public ResultResponse<String> getToken(String name, String chName, String dept){
//        RpcContext.getContext().setAttachment("token", UserInfo.getUser());
        return ResultResponse.success(JwtUtils.getToken(name,chName,dept));
    }

    @GetMapping("/check")
    public ResultResponse<Boolean> checkToken(){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        String token = request.getHeader("Authorization");
        return ResultResponse.success(JwtUtils.checkToken(token));
    }

    @GetMapping("/remote")
    public ResultResponse<String> remote(String para){
        log.info("获取token解析信息："+JSONObject.toJSONString(UserInfo.getUserInfo()));
//        RpcContext.getContext().setAttachment("token", UserInfo.getUser());
//        RpcContext.getContext().setAttachment("user", "sultan");
        return ResultResponse.success(JSONObject.toJSONString(providerInterface.remote(para).getData()));
    }
}
