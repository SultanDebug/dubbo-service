package com.hzq.dubbo.controller;


import com.hzq.dubbo.User;
import com.hzq.dubbo.aop.ResultResponse;
import com.hzq.dubbo.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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
    public ResultResponse<User> getUser(Integer id){
        return ResultResponse.success(service.getById(id));
    }
}
