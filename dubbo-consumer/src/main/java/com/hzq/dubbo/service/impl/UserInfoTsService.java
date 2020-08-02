package com.hzq.dubbo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzq.dubbo.UserInfo;
import com.hzq.dubbo.mapper.UserInfoMapper;
import com.hzq.dubbo.mapper.UserMapper;
import com.hzq.dubbo.service.UserInfoService;
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
