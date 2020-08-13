
package com.hzq.dubbo.config;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.support.ConnectionPoolSupport;
import io.shardingsphere.core.keygen.KeyGenerator;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.time.Duration;

/**
 * 分布式id算法
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/8/12 17:45
 */
public class MyKeyGenerator implements KeyGenerator {

    /**
     * 自增id
     */
    private static final String key = "sharding.id";

    /**
     * 创建redis客户端链接
     */
    private RedisClient client = RedisClient.create(RedisURI.Builder.redis("192.168.215.208",6379).withTimeout(Duration.ofMillis(6000)).withPassword("123456").withDatabase(1).build());
    private GenericObjectPool<StatefulRedisConnection<String, String>> pool = ConnectionPoolSupport.createGenericObjectPool(() -> client.connect(), getGenericObjectPoolConfig());

    /**
     * 获取redis链接
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/8/13 16:46
    */
    private GenericObjectPoolConfig getGenericObjectPoolConfig() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxIdle(8);
        config.setMinIdle(0);
        config.setMaxTotal(8);
        config.setMaxWaitMillis(-1);
        return config;
    }

    /**
     * key获取算法
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/8/13 16:47
    */
    @Override
    public synchronized Number generateKey() {
        try (StatefulRedisConnection<String, String> connection = pool.borrowObject()) {
            RedisAsyncCommands<String, String> commands = connection.async();
            RedisFuture<Long> future = commands.incr(key);
            return future.get().intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return Long.valueOf(System.currentTimeMillis()).intValue();
        }
    }

    /**
     * redis连接池关闭
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/8/13 16:49
    */
    @Override
    protected void finalize() throws Throwable {
        if (!pool.isClosed()){
            pool.close();
        }
        super.finalize();
    }


    /*@Override
    public Number generateKey() {
        RedisTemplate<String,Integer> redisTemplate = ApplicationContextUtils.getApplicationContext().getBean(RedisTemplate.class);
        Boolean aBoolean = redisTemplate.hasKey(key);
        if(aBoolean){
            Long increment = redisTemplate.opsForValue().increment(key);
            return increment;
        }else{
            redisTemplate.opsForValue().set(key,1);
            return 1;
        }
    }*/
}
