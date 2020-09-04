
package com.hzq.dubbo.bussiness.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzq.dubbo.bussiness.mapper.UserMapper;
import com.hzq.dubbo.bussiness.service.BusUserService;
import com.hzq.dubbo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 手动代码  没有继承基础方法
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/7/6 16:39
 */
@Service("busUserServiceImpl")
public class BusUserServiceImpl extends ServiceImpl<UserMapper, User> implements BusUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getById(Integer id) {
        return userMapper.getUser(id);
    }
}
