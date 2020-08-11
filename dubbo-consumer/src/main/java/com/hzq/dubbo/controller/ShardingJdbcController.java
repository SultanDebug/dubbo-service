
package com.hzq.dubbo.controller;

import com.hzq.dubbo.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/8/11 16:55
 */
@RestController
@Slf4j
public class ShardingJdbcController {
    @Autowired
    private IUserService service;


}
