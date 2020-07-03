
package com.hzq.dubbo.service;

import com.hzq.dubbo.aop.UserInfo;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/7/2 11:44
 */
public interface TempService {
    UserInfo getUser();
    Integer getRes(Integer para);
}
