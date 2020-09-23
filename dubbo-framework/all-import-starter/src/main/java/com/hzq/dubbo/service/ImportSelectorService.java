/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.service;

import com.hzq.dubbo.config.HzqProperties;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/9/18 11:52
 */
public class ImportSelectorService {
    @Autowired
    private HzqProperties properties;

    public ImportSelectorService(HzqProperties properties) {
        this.properties = properties;
    }

    public String getConfig(){
        return properties.getName()+":"+properties.getAge();
    }
}
