package com.hzq.dubbo.provider;

import com.hzq.dubbo.aop.ResultResponse;

/**
 * @Description: 服务提供者接口
 * @Auth: Huangzq
 * @Date: Created in 2020/5/23
 */
public interface ProviderInterface {
    ResultResponse<String> remote(String para);
}
