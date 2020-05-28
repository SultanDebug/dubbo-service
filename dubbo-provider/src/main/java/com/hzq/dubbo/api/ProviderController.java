package com.hzq.dubbo.api;

import com.alibaba.dubbo.config.annotation.Service;
import com.hzq.dubbo.aop.UserInfo;
import com.hzq.dubbo.provider.ProviderInterface;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020/5/23
 */
@Service
public class ProviderController implements ProviderInterface {
    @Override
    public String remote(String para) {
        System.out.println(UserInfo.getUser());
        return "hello "+para;
    }
}
