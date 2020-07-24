package com.hzq.dubbo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzq.dubbo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *  Mapper 接口
 *
 * @author huangzq
 * @since 2020-07-24
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    UserInfo get(@Param("id") Integer id);
    Integer update(@Param("id") Integer id,@Param("position") String position );
}
