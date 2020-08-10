package com.hzq.dubbo.controller;


import com.hzq.dubbo.UserInfo;
import com.hzq.dubbo.aop.ResultResponse;
import com.hzq.dubbo.service.UserInfoService;
import com.hzq.dubbo.service.impl.proxy.TsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 事务模拟测试
 *
 * @author huangzq
 * @since 2020-07-07
 */
@RestController
@RequestMapping("/userInfo")
@Slf4j
public class UserInfoController {
    
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private TsService service;

    /**
     * 查询
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/8/2 11:12
    */
    @GetMapping("/get")
    public ResultResponse<UserInfo> getUser(@RequestParam("id") Integer id){
        return ResultResponse.success(userInfoService.getById(id));
    }

    /**
     * 新增
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/8/2 11:12
     */
    @PostMapping("/add")
    public ResultResponse<Boolean> addUser(@RequestBody UserInfo user){
        return ResultResponse.success(userInfoService.save(user));
    }

    /**
     * 更新
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/8/2 11:12
     */
    @PostMapping("/update")
    public ResultResponse<Boolean> updateUser(@RequestBody UserInfo user){
        return ResultResponse.success(userInfoService.saveOrUpdate(user));
    }

    /**
     * 删除
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/8/2 11:12
     */
    @GetMapping("/delete")
    public ResultResponse<Boolean> deleteUser(@RequestParam("id") Integer id){
        return ResultResponse.success(userInfoService.removeById(id));
    }

    /**
     * 事务测试
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/8/2 11:12
     */
    @GetMapping("/ts")
    public ResultResponse<String> transaction(@RequestParam("id") Integer id,@RequestParam("para") String para){
        return ResultResponse.success(userInfoService.transactionValid(id,para));
    }

    /**
     * 事物模拟测试  内部调用代理失效问题
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/7/24 16:35
    */
    @GetMapping("/tsTest")
    public ResultResponse<Boolean> tsTest(){
        log.info("直接执行");
        service.methodOne();

        log.info("混合执行");
        service.mid();
        return ResultResponse.success();
    }

}
