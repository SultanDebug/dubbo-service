package com.hzq.dubbo.api;

import com.hzq.dubbo.aop.ResultResponse;
import com.hzq.dubbo.aop.UserInfo;
import com.hzq.dubbo.provider.ProviderInterface;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020/5/23
 */
@Service
@Slf4j
public class ProviderController implements ProviderInterface {
    @Override
    public ResultResponse<String> remote(String para) {
        log.info("获取用户信息：{} ，token：{}",UserInfo.getUserInfo(),UserInfo.getUser());
        return ResultResponse.success("hello "+para);
    }
}
