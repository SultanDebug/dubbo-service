package com.hzq.dubbo.controller;


import com.hzq.dubbo.UserInfo;
import com.hzq.dubbo.aop.ResultResponse;
import com.hzq.dubbo.service.UserInfoService;
import com.hzq.dubbo.user.entity.User;
import com.hzq.dubbo.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author huangzq
 * @since 2020-07-07
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {
    
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/get")
    public ResultResponse<UserInfo> getUser(@RequestParam("id") Integer id){
        return ResultResponse.success(userInfoService.getById(id));
    }

    @PostMapping("/add")
    public ResultResponse<Boolean> addUser(@RequestBody UserInfo user){
        return ResultResponse.success(userInfoService.save(user));
    }

    @PostMapping("/update")
    public ResultResponse<Boolean> updateUser(@RequestBody UserInfo user){
        return ResultResponse.success(userInfoService.saveOrUpdate(user));
    }

    @GetMapping("/delete")
    public ResultResponse<Boolean> deleteUser(@RequestParam("id") Integer id){
        return ResultResponse.success(userInfoService.removeById(id));
    }

    @GetMapping("/ts")
    public ResultResponse<Boolean> transaction(@RequestParam("id") Integer id,@RequestParam("para") String para){
        return ResultResponse.success(userInfoService.transactionValid(id,para));
    }

}
