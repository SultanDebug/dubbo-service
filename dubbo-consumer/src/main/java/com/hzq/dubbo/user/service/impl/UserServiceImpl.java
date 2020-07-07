package com.hzq.dubbo.user.service.impl;

import com.hzq.dubbo.user.entity.User;
import com.hzq.dubbo.user.mapper.GUserMapper;
import com.hzq.dubbo.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huangzq
 * @since 2020-07-07
 */
@Service("user1")
public class UserServiceImpl extends ServiceImpl<GUserMapper, User> implements IUserService {

}
