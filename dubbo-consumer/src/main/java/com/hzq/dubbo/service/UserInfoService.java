package com.hzq.dubbo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hzq.dubbo.UserInfo;

/**
 *  服务类
 *
 * @author huangzq
 * @since 2020-07-24
 */
public interface UserInfoService extends IService<UserInfo> {



    /**
     * 事务测试
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/7/24 13:43
    */
    String transactionValid(Integer id ,String para);
}
