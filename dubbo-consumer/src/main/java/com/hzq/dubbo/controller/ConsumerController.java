package com.hzq.dubbo.controller;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hzq.dubbo.User;
import com.hzq.dubbo.aop.ResultResponse;
import com.hzq.dubbo.aop.UserInfo;
import com.hzq.dubbo.bussinessutil.BussinessUtils;
import com.hzq.dubbo.dto.TempDto;
import com.hzq.dubbo.jwt.JwtUtils;
import com.hzq.dubbo.provider.ProviderInterface;
import com.hzq.dubbo.service.CacheEventService;
import com.hzq.dubbo.service.TempService;
import com.hzq.dubbo.service.UserService;
import com.hzq.dubbo.websocket.WebsocketServer;
import com.hzq.redis.cache.CacheUtil;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicInteger;

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
    @Reference(check = false/*,url = "dubbo://192.168.50.154:21002"*/,loadbalance = "consistenthash")
    private ProviderInterface providerInterface;

    @Resource
    private RedisTemplate<String,UserInfo> redisTemplate;

    @Autowired
    private CacheEventService<UserInfo> cacheEventService;

    @Autowired
    private CacheUtil cacheUtil;

    @Autowired
    private TempService tempService;

    @Autowired
    private UserService userService;

    /**
     * token生成
     * @param name
     * @param chName
     * @param dept
     * @return
     */
    @PostMapping("/login")
    public ResultResponse<String> getToken(String name, String chName, String dept){
//        RpcContext.getContext().setAttachment("token", UserInfo.getUser());
        return ResultResponse.success(JwtUtils.getToken(name,chName,dept));
    }

    /**
     * token有效性检查
     * @return
     */
    @GetMapping("/check")
    public ResultResponse<Boolean> checkToken(){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        String token = request.getHeader("Authorization");
        return ResultResponse.success(JwtUtils.checkToken(token));
    }

    /**
     * redis测试
     * @param key
     * @return
     */
    @GetMapping("/redis")
    public ResultResponse<UserInfo> redis(String key){
//        UserInfo userInfo = UserInfo.getUserInfo();

        UserInfo cache = cacheUtil.getCache(tempService::getUser, key, UserInfo.class, 30L);

        log.info("拿到数据："+cache);
        /*ValueOperations<String, UserInfo> ops = redisTemplate.opsForValue();
        ops.set("key-1",userInfo);*/

        Boolean aBoolean = redisTemplate.hasKey(key);
        log.info("redis存在{}数据：{}",key,aBoolean);

//        UserInfo userInfo1 = ops.get("key-1");

        return ResultResponse.success(cache);
    }


    /**
     * spring事件测试
     * @return
     */
    @GetMapping("/event")
    public ResultResponse<UserInfo> event(){
        UserInfo userInfo = UserInfo.getUserInfo();
        cacheEventService.publishEvent(userInfo);
        return ResultResponse.success(userInfo);
    }

    /**
     * 适配器测试
     * @param type
     * @param para
     * @return
     */
    @GetMapping("/adapt")
    public ResultResponse<String> adapt(Integer type , String para){
        String demo = BussinessUtils.get(type).demo(para);
        return ResultResponse.success(demo);
    }

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

    public TempDto tempDto = new TempDto();

    /**
     * 并发测试 无效
     * @param para
     * @return
     */
    @GetMapping("/concur")
    public ResultResponse<Integer> concur(Integer para){
        Integer a = tempDto.getConCur();
//        val.getAndAdd(para);

        Integer b = a + para;
        tempDto.setConCur(b);
        if(!tempDto.getConCur().equals(b)){
            log.error("获取数据：{}",tempDto.getConCur());
        }
        return ResultResponse.success(a);
    }

    @GetMapping("/socket")
    public ResultResponse<String> socket(String sid,String msg){
        WebsocketServer.sendInfo(msg,sid);
        return ResultResponse.success("成功");
    }

    @GetMapping("/getUser")
    public ResultResponse<User> getUser(Integer id){
        return ResultResponse.success(userService.getById(id));
    }
}
