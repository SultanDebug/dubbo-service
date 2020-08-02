
package com.hzq.dubbo.service;

import com.hzq.dubbo.User;

/**
 * 手动mybatis代码
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/7/6 16:38
 */
public interface UserService {
    User getById(Integer id);
}
