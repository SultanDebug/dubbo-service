
package com.hzq.dubbo.bussiness.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
public interface UserMapper extends BaseMapper<User> {
    User getUser(Integer id);
    IPage<User> getAllUserByPage(Page<User> page);
    Integer updateUser(Integer id, String name);
}
