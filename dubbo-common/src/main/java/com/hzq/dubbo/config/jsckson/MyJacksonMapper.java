
package com.hzq.dubbo.config.jsckson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.sun.corba.se.spi.ior.ObjectId;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2021/4/30 10:47
 */
public class MyJacksonMapper extends ObjectMapper {

    private static final long serialVersionUID = -9006477671206460204L;

    MyJacksonMapper(){
        SimpleModule module = new SimpleModule("globalReplace");
        module.addSerializer(String.class, new MyJacksonSerialize());
        this.registerModule(module);
    }
}
