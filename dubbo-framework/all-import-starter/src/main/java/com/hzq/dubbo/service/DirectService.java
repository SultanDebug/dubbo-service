
package com.hzq.dubbo.service;

import com.hzq.dubbo.config.HzqProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

/**
 * config形式
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/9/18 11:52
 */
public class DirectService {

    private HzqProperties properties;

    public DirectService(HzqProperties properties) {
        this.properties = properties;
    }

    public String getConfig(){
        return properties.getName()+":"+properties.getAge();
    }
}
