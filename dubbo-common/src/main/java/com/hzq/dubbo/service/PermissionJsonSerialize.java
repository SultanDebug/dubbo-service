/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzq.dubbo.dto.Permission;
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

    public static void main(String[] args) throws IOException {
        String s = "{\n" +
                "  \"menus\": [\n" +
                "    {\n" +
                "      \"code\": \"platform.user\",\n" +
                "      \"name\": \"用户\",\n" +
                "      \"type\": \"PLATFORM\",\n" +
                "      \"children\": [\n" +
                "        {\n" +
                "          \"code\": \"platform.user.update\",\n" +
                "          \"name\": \"用户更新\",\n" +
                "          \"type\": \"PLATFORM\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"code\": \"platform.user.delete\",\n" +
                "          \"name\": \"用户删除\",\n" +
                "          \"type\": \"PLATFORM\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"code\": \"platform.setting\",\n" +
                "      \"name\": \"设置\",\n" +
                "      \"type\": \"PLATFORM\",\n" +
                "      \"children\": [\n" +
                "        {\n" +
                "          \"code\": \"platform.setting.list\",\n" +
                "          \"name\": \"列表设置\",\n" +
                "          \"type\": \"PLATFORM\",\n" +
                "          \"children\": [\n" +
                "            {\n" +
                "              \"code\": \"platform.setting.list.update\",\n" +
                "              \"name\": \"列表编辑\",\n" +
                "              \"type\": \"PLATFORM\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"code\": \"platform.setting.list.export\",\n" +
                "              \"name\": \"列表导出\",\n" +
                "              \"type\": \"PLATFORM\"\n" +
                "            }\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"code\": \"platform.setting.bussiness\",\n" +
                "          \"name\": \"业务设置\",\n" +
                "          \"type\": \"PLATFORM\",\n" +
                "          \"children\": [\n" +
                "            {\n" +
                "              \"code\": \"platform.setting.bussiness.one\",\n" +
                "              \"name\": \"业务设置一\",\n" +
                "              \"type\": \"PLATFORM\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"code\": \"platform.setting.bussiness.two\",\n" +
                "              \"name\": \"业务设置二\",\n" +
                "              \"type\": \"PLATFORM\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        ObjectMapper mapper = new ObjectMapper();
        Permission permission = mapper.readValue(s, Permission.class);
        System.out.println(permission);
    }
}
