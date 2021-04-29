/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo;

import lombok.Data;
import org.junit.Test;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2021/4/29 10:24
 */
@Data
public class CopyDto implements Cloneable{
    public Integer i;
    public CopyVo vo;
}
