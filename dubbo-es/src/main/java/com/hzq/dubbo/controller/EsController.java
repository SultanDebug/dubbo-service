package com.hzq.dubbo.controller;


import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.api.naming.pojo.ServiceInfo;
import com.hzq.dubbo.aop.ResultResponse;
import com.hzq.dubbo.jwt.JwtUtils;
import com.hzq.dubbo.provider.ProviderInterface;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author huangzq
 * @since 2020-07-07
 */
@RestController
@RequestMapping("/base")
public class EsController {
    @Reference(check = false/*,url = "dubbo://192.168.50.154:21002"*/,loadbalance = "consistenthash")
    private ProviderInterface providerInterface;

    /*@Autowired
    private NamingService namingService;*/

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/login")
    public ResultResponse<String> getToken(String name, String chName, String dept){
//        RpcContext.getContext().setAttachment("token", UserInfo.getUser());
        return ResultResponse.success(JwtUtils.getToken(name,chName,dept));
    }

    @GetMapping("/remote")
    public ResultResponse<String> remote(@RequestParam("id") Integer id){
        ResultResponse<String> remote = providerInterface.remote(id + "");
        return remote;
    }

    /*@GetMapping("/getServ")
    public ResultResponse<List<ServiceInfo>> getServ() throws NacosException {
        return ResultResponse.success(namingService.getSubscribeServices());
    }

    @GetMapping("/getIns")
    public ResultResponse<List<Instance>> getIns(@RequestParam String serviceName) throws NacosException {
        return ResultResponse.success(namingService.getAllInstances(serviceName));
    }*/

    @GetMapping("/getServ")
    public ResultResponse<List<String>> getServ() throws NacosException {
        return ResultResponse.success(discoveryClient.getServices());
    }

    @GetMapping("/getIns")
    public ResultResponse<List<ServiceInstance>> getIns(@RequestParam String serviceName) throws NacosException {
        return ResultResponse.success(discoveryClient.getInstances(serviceName));
    }

}
