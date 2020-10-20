
package com.hzq.dubbo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzq.dubbo.dto.Permission;
import com.hzq.dubbo.dto.PermissionDTO;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/10/16 16:50
 */
public class PermissionJsonSerialize {
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

    public static void getList(PermissionDTO dto,List<PermissionDTO> list){
        list.add(dto);
        if(!CollectionUtils.isEmpty(dto.getChildren())){
            dto.getChildren().forEach(o->{
                o.setParentCode(dto.getCode());
                getList(o,list);
            });
        }
    }

    public static void getMap(PermissionDTO dto, Map<String,PermissionDTO> map){
        map.put(dto.getCode(),dto);
        if(!CollectionUtils.isEmpty(dto.getChildren())){
            dto.getChildren().forEach(o->{
                o.setParentCode(dto.getCode());
                getMap(o,map);
            });
        }
    }

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
}
