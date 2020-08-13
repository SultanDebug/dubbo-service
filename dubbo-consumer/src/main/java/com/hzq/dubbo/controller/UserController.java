package com.hzq.dubbo.controller;


import com.hzq.dubbo.aop.ResultResponse;
import com.hzq.dubbo.service.UserInfoService;
import com.hzq.dubbo.user.entity.User;
import com.hzq.dubbo.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * mybatisplus测试
 *
 * @author huangzq
 * @since 2020-07-07
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService service;

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 查询
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/8/2 11:11
    */
    @GetMapping("/getUser")
    public ResultResponse<User> getUser(@RequestParam("id") Integer id){
        return ResultResponse.success(service.getById(id));
    }

    /**
     * 新增
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/8/2 11:11
     */
    @PostMapping("/addUser")
    public ResultResponse<Integer> addUser(@RequestBody User user){
        boolean save = service.save(user);
        return ResultResponse.success(user.getId());
    }

    /**
     * 更新
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/8/2 11:11
     */
    @PostMapping("/updateUser")
    public ResultResponse<Boolean> updateUser(@RequestBody User user){
        return ResultResponse.success(service.saveOrUpdate(user));
    }

    /**
     * 删除
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/8/2 11:11
     */
    @GetMapping("/deleteUser")
    public ResultResponse<Boolean> deleteUser(@RequestParam("id") Integer id){
        return ResultResponse.success(service.removeById(id));
    }
}
