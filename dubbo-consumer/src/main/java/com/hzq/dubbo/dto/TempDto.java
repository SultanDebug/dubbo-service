
package com.hzq.dubbo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/7/2 14:31
 */
@Data
public class TempDto implements Serializable {
    private static final long serialVersionUID = -5142519739009397155L;

    private Integer conCur = 1;
}
