/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.service.impl;

import com.alibaba.fastjson.JSON;
import com.hzq.dubbo.aop.UserInfo;
import com.hzq.dubbo.service.TempService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/7/2 11:44
 */
@Slf4j
@Service
public class TempServiceImpl implements TempService {
    private Integer val = 1;

    @Override
    public UserInfo getUser() {
        String s = "{\"chName\":\"黄大狗\",\"dept\":\"黄大狗部门\",\"name\":\"sultan\"}";
        UserInfo userInfo = JSON.parseObject(s, UserInfo.class);
        return userInfo;
    }

    @Override
    public Integer getRes(Integer para) {
        val = val + para;
        return val;
    }
}
