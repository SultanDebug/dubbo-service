/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/9/18 11:44
 */
@ConfigurationProperties(prefix = "hzq")
@ConditionalOnProperty(prefix = "hzq",value = "enable",matchIfMissing = true)
@Data
public class HzqProperties {
    private String name;
    private Integer age;
}
