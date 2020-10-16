/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzq.dubbo.dto.PermissionDTO;
import org.springframework.boot.json.JacksonJsonParser;

import java.io.*;
import java.util.List;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/10/16 16:50
 */
public class PermissionJsonSerialize {
    public static PermissionDTO parsJson() throws IOException {
        InputStream in = PermissionJsonSerialize.class.getResourceAsStream("/permission/menu.json");
        byte[] bytes = new byte[1024];
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int pos = 0;
        while((pos = in.read(bytes)) != -1){
            outputStream.write(bytes,0,pos);
        }
        bytes = outputStream.toByteArray();
        String jsonString = new String(bytes);

        List<PermissionDTO> permissionDTOs = JSONArray.parseArray(jsonString,PermissionDTO.class);
        return permissionDTOs.get(0);
    }
}
