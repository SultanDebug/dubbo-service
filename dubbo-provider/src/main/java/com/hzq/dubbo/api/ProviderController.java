package com.hzq.dubbo.api;

import com.alibaba.fastjson.JSONObject;
import com.hzq.dubbo.aop.ResultResponse;
import com.hzq.dubbo.aop.UserInfo;
import com.hzq.dubbo.provider.ProviderInterface;
import com.hzq.dubbo.spi.Person;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 远程服务提供者
 *
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020/5/23
 */
@Service
@Slf4j
public class ProviderController implements ProviderInterface {

    /**
     * 远程测试
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/8/2 12:31
    */
    @Override
    public ResultResponse<String> remote(String para) {
        log.info("获取用户信息：{} ，token：{}",UserInfo.getUserInfo(),UserInfo.getUser());
        return ResultResponse.success("hello "+para);
    }

    /**
     * spi测试
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/8/2 12:31
    */
    @Override
    public ResultResponse<String> remoteSPI(String para) {
        ServiceLoader<Person> load = ServiceLoader.load(Person.class);
        AtomicInteger i = new AtomicInteger(1);
        List<String> strings = new ArrayList<>();
        load.forEach(o->{

            log.info(JSONObject.toJSONString(o.getMsg(i.get()+"")));
            strings.add(o.getMsg(i.get()+"").getMsg());
            i.getAndIncrement();
        });
        return ResultResponse.success(JSONObject.toJSONString(strings));
    }
}
