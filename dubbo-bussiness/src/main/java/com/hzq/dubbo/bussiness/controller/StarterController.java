/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.bussiness.controller;

import com.hzq.dubbo.aop.ResultResponse;
import com.hzq.dubbo.enableautoconfig.DirectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/9/18 16:54
 */
@RestController
@RequestMapping("/starter")
public class StarterController {
    @Autowired(required = false)
    private DirectService directService;
    @GetMapping("/get")
    public ResultResponse<String> remote(){
        return ResultResponse.success(directService.getConfig());
    }
}
