package com.hzq.dubbo.controller;


import com.hzq.dubbo.aop.ResultResponse;
import com.hzq.dubbo.user.entity.User;
import com.hzq.dubbo.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author huangzq
 * @since 2020-07-07
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService service;

    @GetMapping("/getUser")
    public ResultResponse<User> getUser(@RequestParam("id") Integer id){
        return ResultResponse.success(service.getById(id));
    }

    @PostMapping("/addUser")
    public ResultResponse<Boolean> addUser(@RequestBody User user){
        return ResultResponse.success(service.save(user));
    }

    @PostMapping("/updateUser")
    public ResultResponse<Boolean> updateUser(@RequestBody User user){
        return ResultResponse.success(service.saveOrUpdate(user));
    }

    @GetMapping("/deleteUser")
    public ResultResponse<Boolean> deleteUser(@RequestParam("id") Integer id){
        return ResultResponse.success(service.removeById(id));
    }
}
