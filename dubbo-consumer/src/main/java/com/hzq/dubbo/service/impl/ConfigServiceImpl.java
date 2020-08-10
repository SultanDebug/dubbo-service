
package com.hzq.dubbo.service.impl;

import com.hzq.dubbo.config.Person;
import com.hzq.dubbo.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/8/7 17:08
 */
@Service
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    private Person person;
    @Override
    public Person getPerson() {
        return person;
    }
}
