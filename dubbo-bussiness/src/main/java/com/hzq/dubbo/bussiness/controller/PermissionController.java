
package com.hzq.dubbo.bussiness.controller;

import com.hzq.dubbo.annotation.Auth;
import com.hzq.dubbo.annotation.ParentKey;
import com.hzq.dubbo.aop.ResultResponse;
import com.hzq.dubbo.dto.Permission;
import com.hzq.dubbo.dto.PermissionDTO;
import com.hzq.dubbo.dto.ProcessDTO;
import com.hzq.dubbo.service.PermissionJsonSerialize;
import com.hzq.dubbo.service.ProcessService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 权限点验证  字段值修改验证
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/10/16 17:09
 */
@RestController
@RequestMapping("/permission")
@ParentKey(code = "platform.user.update")
public class PermissionController {
    /**
     * 权限点测试
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/10/21 11:50
    */
    @GetMapping("/parse")
    @Auth(code = "zhangsan",name = "张三")
    public ResultResponse<Permission> parse() throws IOException {
        Permission permission = PermissionJsonSerialize.parsJson();

        List<PermissionDTO> menus = permission.getMenus();

        //list  组装父code
        List<PermissionDTO> list = new ArrayList<>();

        menus.forEach(o->{
            PermissionJsonSerialize.getList(o,list);
        });

        //map  组装父code
        Map<String,PermissionDTO> map = new HashMap<>();

        menus.forEach(o->{
            PermissionJsonSerialize.getMap(o,map);
        });

        //组装树
        Permission tree = new Permission();

        List<PermissionDTO> parent = list.stream().filter(o-> StringUtils.isEmpty(o.getParentCode())).collect(Collectors.toList());
        Map<String,List<PermissionDTO>> parentMap = list.stream().filter(o-> !StringUtils.isEmpty(o.getParentCode()))
                .collect(Collectors.groupingBy(PermissionDTO::getParentCode));

        List<PermissionDTO> treeMenus = new ArrayList<>();

        parent.forEach(o->{
            PermissionJsonSerialize.getTree(o,parentMap);
            treeMenus.add(o);
        });
        tree.setMenus(treeMenus);

        return ResultResponse.success(permission);
    }

    /**
     * 字段值测试
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/10/21 11:51
    */
    @PostMapping("/annoProcess")
    @Auth(code = "lisi",name = "李四")
    public ResultResponse<Object> annoProcess(@RequestBody ProcessDTO processDTO) throws IOException, InstantiationException, IllegalAccessException {
        ProcessDTO dto = new ProcessDTO();
        dto.setId("1");
        dto.setName("sultan");

        ProcessDTO dto1 = new ProcessDTO();
        dto1.setId("2");
        dto1.setName("sultan");

        ProcessDTO dto2 = new ProcessDTO();
        dto2.setId("3");
        dto2.setName("sultan");

        dto.setList(Arrays.asList(dto1,dto2));

        ProcessDTO dto3 = new ProcessDTO();
        dto3.setId("4");
        dto3.setName("sultan");

        Map<String ,ProcessDTO> map = new HashMap<>();
        map.put(dto3.getId(),dto3);
        dto.setMap(map);

//        ProcessService.process(dto);
        return ResultResponse.success(dto);
    }
}
