package com.hzq.dubbo.bussiness.service.impl;

import com.hzq.dubbo.bussiness.mapper.UserInfoMapper;
import com.hzq.dubbo.bussiness.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *  服务实现类
 *
 * @author huangzq
 * @since 2020-07-24
 */
@Service
public class UserInfoTsService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 事务传播机制测试
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/8/2 11:21
    */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateUser(Integer id ,String para){
        userMapper.updateUser(id,para);
    }

    /**
     * 事务传播机制测试
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/8/2 11:21
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateUserInfo(Integer id ,String para){
        userInfoMapper.update(id,para);
        if(id == 1){
            throw new RuntimeException("事务测试异常");
        }
    }
}
