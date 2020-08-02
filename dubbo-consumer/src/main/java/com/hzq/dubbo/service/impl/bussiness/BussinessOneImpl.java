
package com.hzq.dubbo.service.impl.bussiness;

import com.hzq.dubbo.bussinessutil.BussinessUtils;
import com.hzq.dubbo.service.BussinessBaseService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * 适配器一号业务
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/6/24 17:37
 */
@Service
public class BussinessOneImpl implements BussinessBaseService, InitializingBean {
    @Override
    public String demo(String para) {
        return "one:"+para;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        BussinessUtils.add(1,this);
    }
}
