/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.mapper;

import com.hzq.dubbo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/7/6 16:35
 */
@Mapper
public interface UserMapper  {
    User getUser(Integer id);
    Integer updateUser(Integer id, String name);
}
