
package com.hzq.dubbo.bussiness.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hzq.dubbo.User;
import com.hzq.dubbo.bussiness.dto.UserPageRequest;

/**
 * 手动mybatis代码
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/7/6 16:38
 */
public interface BusUserService extends IService<User> {
    User getById(Integer id);
    IPage<User> getAllUserByPage(UserPageRequest request);
}
