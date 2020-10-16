
package com.hzq.dubbo.bussiness.controller;

import com.hzq.dubbo.aop.ResultResponse;
import com.hzq.dubbo.dto.PermissionDTO;
import com.hzq.dubbo.service.PermissionJsonSerialize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/10/16 17:09
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {
    @GetMapping("/parse")
    public ResultResponse<PermissionDTO> parse() throws IOException {
        return ResultResponse.success(PermissionJsonSerialize.parsJson());
    }
}
