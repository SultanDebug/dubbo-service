/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.service;

import com.hzq.dubbo.User;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/7/6 16:38
 */
public interface UserService {
    User getById(Integer id);
}
