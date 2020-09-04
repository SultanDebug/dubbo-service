package com.hzq.dubbo.bussiness.controller;


import com.hzq.dubbo.UserInfo;
import com.hzq.dubbo.aop.ResultResponse;
import com.hzq.dubbo.bussiness.proxy.TsService;
import com.hzq.dubbo.bussiness.service.BusUserInfoService;
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
    private BusUserInfoService setviceA;

    @Autowired
    private TsService tsService;

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
    public ResultResponse<UserInfo> getUserInfo(@RequestParam("id") Integer id){
        return ResultResponse.success(setviceA.getById(id));
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
    public ResultResponse<Boolean> addUserInfo(@RequestBody UserInfo userInfo){
        return ResultResponse.success(setviceA.save(userInfo));
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
    public ResultResponse<Boolean> updateUserInfo(@RequestBody UserInfo userInfo){
        return ResultResponse.success(setviceA.saveOrUpdate(userInfo));
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
    public ResultResponse<Boolean> deleteUserInfo(@RequestParam("id") Integer id){
        return ResultResponse.success(setviceA.removeById(id));
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
        return ResultResponse.success(setviceA.transactionValid(id,para));
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
        tsService.methodOne();

        log.info("混合执行");
        tsService.mid();
        return ResultResponse.success();
    }

}
