
package com.hzq.dubbo.config;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Range;
import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.RangeShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import io.shardingsphere.api.algorithm.sharding.standard.RangeShardingAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 分表算法
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/8/12 15:13
 */
@Slf4j
public class MyTableShardingAlgorithm implements PreciseShardingAlgorithm<String>{
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
        log.info("tableNames:{}", JSON.toJSONString(collection));
        log.info("shardingValue:{}", JSON.toJSONString(preciseShardingValue));

        String tableName = "";

        if ("user".equalsIgnoreCase(preciseShardingValue.getLogicTableName())) {
            for (String each : collection) {
                if (each.endsWith((preciseShardingValue.getValue().hashCode() % collection.size()+1) + "")) {
                    log.info("table:{}", each);
                    tableName = each;
                    break;
                }
            }
        }

        // 分库分表
        /*if ("t_order".equalsIgnoreCase(preciseShardingValue.getLogicTableName()) ||
                "t_order_item".equalsIgnoreCase(preciseShardingValue.getLogicTableName())) {
            for (String each : collection) {
                if (each.endsWith(preciseShardingValue.getValue() % collection.size() + "")) {
                    tableName = each;
                    break;
                }
            }
        }*/

        log.info("tableName:{}", tableName);
        if (!StringUtils.isEmpty(tableName)) {
            return tableName;
        }

        throw new UnsupportedOperationException();
    }

    /*@Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<String> rangeShardingValue) {
        log.info("collection:{}", JSON.toJSONString(collection));
        log.info("rangeShardingValue:{}", JSON.toJSONString(rangeShardingValue));

        Collection<String> collect = new ArrayList<>();
        Range<String> valueRange = rangeShardingValue.getValueRange();
        for (int i = valueRange.lowerEndpoint(); i <= valueRange.upperEndpoint(); i++) {
            for (String each : collection) {
                if (each.endsWith(i % collection.size() + "")) {
                    collect.add(each);
                }
            }
        }

        return collect;
    }*/
}
