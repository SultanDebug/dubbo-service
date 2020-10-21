
package com.hzq.dubbo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 权限实体
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/10/16 16:52
 */
@Data
public class PermissionDTO implements Serializable {
    private static final long serialVersionUID = 1938732970897738104L;
    public static final String defaultType = "PLATFORM";
    private String code;
    private String parentCode;
    private String name;
    private String type;
    private List<PermissionDTO> children;
}
