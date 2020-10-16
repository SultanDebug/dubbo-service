/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/10/16 16:52
 */
@Data
public class Permission implements Serializable {
    private static final long serialVersionUID = 1938732970897738104L;
    private List<PermissionDTO> menus;
}
