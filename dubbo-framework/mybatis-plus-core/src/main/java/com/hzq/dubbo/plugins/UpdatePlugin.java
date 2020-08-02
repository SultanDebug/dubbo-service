
package com.hzq.dubbo.plugins;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * 更新插件拦截器
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/7/13 11:16
 */
@Intercepts(value = {
        @Signature(type = Executor.class,
                method = "update",
                args = {MappedStatement.class,Object.class}
        )
})
@Slf4j
public class UpdatePlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement o = (MappedStatement) invocation.getArgs()[0];

        if(o.getSqlCommandType().equals(SqlCommandType.INSERT)){
            log.info("进入新增拦截器{},{}",invocation.getClass().getSimpleName(),invocation.getMethod().getName());
        }else if(o.getSqlCommandType().equals(SqlCommandType.UPDATE)){
            log.info("进入更新拦截器{},{}",invocation.getClass().getSimpleName(),invocation.getMethod().getName());
        }else if(o.getSqlCommandType().equals(SqlCommandType.DELETE)){
            log.info("进入删除拦截器{},{}",invocation.getClass().getSimpleName(),invocation.getMethod().getName());
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {
        log.info("参数注入{}",properties.toString());
    }
}
