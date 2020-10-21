
package com.hzq.dubbo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzq.dubbo.dto.Permission;
import com.hzq.dubbo.dto.PermissionDTO;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 权限数据处理  转list  转map  转tree
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/10/16 16:50
 */
public class PermissionJsonSerialize {
    /**
     * 读取json文件
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/10/21 11:48
    */
    public static Permission parsJson() throws IOException {
        InputStream in = PermissionJsonSerialize.class.getResourceAsStream("/permission/menu.json");
        byte[] bytes = new byte[1024];
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int pos = 0;
        while((pos = in.read(bytes)) != -1){
            outputStream.write(bytes,0,pos);
        }
        bytes = outputStream.toByteArray();
        String jsonString = new String(bytes);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonString, Permission.class);
    }

    /**
     * 转list
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/10/21 11:49
    */
    public static void getList(PermissionDTO dto,List<PermissionDTO> list){
        list.add(dto);
        if(!CollectionUtils.isEmpty(dto.getChildren())){
            dto.getChildren().forEach(o->{
                o.setParentCode(dto.getCode());
                getList(o,list);
            });
        }
    }
    /**
     * 转map
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/10/21 11:49
     */
    public static void getMap(PermissionDTO dto, Map<String,PermissionDTO> map){
        map.put(dto.getCode(),dto);
        if(!CollectionUtils.isEmpty(dto.getChildren())){
            dto.getChildren().forEach(o->{
                o.setParentCode(dto.getCode());
                getMap(o,map);
            });
        }
    }
    /**
     * 转tree
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/10/21 11:49
     */
    public static void getTree(PermissionDTO parent,Map<String,List<PermissionDTO>> map) {
        List<PermissionDTO> permissionDTOS = map.get(parent.getCode());
        if(!CollectionUtils.isEmpty(permissionDTOS)){
            List<PermissionDTO> children = new ArrayList<>(permissionDTOS);
            parent.setChildren(children);
            for (PermissionDTO child : children) {
                getTree(child,map);
            }
        }
    }
    /**
     * 读取注解后所有权限点组装
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/10/21 11:49
     */
    public static Permission getAll(List<PermissionDTO> dtos) throws IOException {

        //获取json
        Permission permission = PermissionJsonSerialize.parsJson();

        List<PermissionDTO> menus = permission.getMenus();

        //list  组装父code
        List<PermissionDTO> list = new ArrayList<>();

        menus.forEach(o->{
            PermissionJsonSerialize.getList(o,list);
        });

        //新增注解数据
        list.addAll(dtos);

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

        return tree;
    }
}
