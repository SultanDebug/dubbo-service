/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.filter;

import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeFilter;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/10/19 15:57
 */
public class NullFilter implements PropertyFilter {

    @Override
    public boolean apply(Object object, String name, Object value) {
        return value != null;
    }
}
