package com.hzq.dubbo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.api.naming.pojo.ServiceInfo;
import com.hzq.dubbo.aop.ResultResponse;
import com.hzq.dubbo.jwt.JwtUtils;
import com.hzq.dubbo.provider.ProviderInterface;
import com.hzq.dubbo.service.EsService;
import com.hzq.dubbo.util.EsUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  前端控制器
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

    @Autowired
    private EsService esService;

    /**
     * 服务网关token获取
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/8/2 11:51
    */
    @PostMapping("/login")
    public ResultResponse<String> getToken(String name, String chName, String dept){
//        RpcContext.getContext().setAttachment("token", UserInfo.getUser());
        return ResultResponse.success(JwtUtils.getToken(name,chName,dept));
    }

    /**
     * 远程测试
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/8/2 11:51
    */
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

    /**
     * 获取nacos注册服务信息
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/8/2 11:52
    */
    @GetMapping("/getServ")
    public ResultResponse<List<String>> getServ() throws NacosException {
        return ResultResponse.success(discoveryClient.getServices());
    }

    /**
     * 获取服务实例列表
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/8/2 11:52
    */
    @GetMapping("/getIns")
    public ResultResponse<List<ServiceInstance>> getIns(@RequestParam String serviceName) throws NacosException {
        return ResultResponse.success(discoveryClient.getInstances(serviceName));
    }

    /**
     * 根据index【/nba/nba】获取一条es数据
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/8/2 11:52
    */
    @GetMapping("/getIdx")
    public ResultResponse<Object> getIdx(@RequestParam String idx) {
        return ResultResponse.success(JSON.parseObject(esService.getByIdx(idx)));
    }

}
