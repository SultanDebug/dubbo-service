/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.bussiness.controller;

import com.hzq.dubbo.aop.ResultResponse;
import com.hzq.dubbo.service.DirectService;
import com.hzq.dubbo.service.ImportSelectorService;
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
    @Autowired(required = false)
    private ImportSelectorService importSelectorService;

    @GetMapping("/get")
    public ResultResponse<String> get(){
        return ResultResponse.success(directService.getConfig());
    }

    @GetMapping("/getSelector")
    public ResultResponse<String> getSelector(){
        return ResultResponse.success(importSelectorService.getConfig());
    }
}
