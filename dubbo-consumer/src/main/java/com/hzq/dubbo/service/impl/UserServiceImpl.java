
package com.hzq.dubbo.service.impl;

import com.hzq.dubbo.User;
import com.hzq.dubbo.mapper.UserMapper;
import com.hzq.dubbo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 手动代码  没有继承基础方法
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/7/6 16:39
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getById(Integer id) {
        return userMapper.getUser(id);
    }
}
