
package com.hzq.dubbo.service;

import com.alibaba.fastjson.JSON;
import com.hzq.dubbo.annotation.Auth;
import com.hzq.dubbo.annotation.ParentKey;
import com.hzq.dubbo.dto.Permission;
import com.hzq.dubbo.dto.PermissionDTO;
import com.sun.org.apache.bcel.internal.generic.ATHROW;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 权限点自动加载
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/10/20 16:56
 */
@Order
@Component
@Slf4j
public class LoadPermissionResource implements CommandLineRunner {

    private List<String> classes = new ArrayList<>();

    /**
     * 主入口
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/10/21 11:46
    */
    @Override
    public void run(String... args) throws Exception {
        String pkg = "com.hzq.dubbo.bussiness.controller";

        scan(pkg);
        getData();

    }

    /**
     * 扫描注解
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/10/21 11:47
    */
    private void scan(String pkg) throws ClassNotFoundException {
        String path = Objects.requireNonNull(this.getClass().getClassLoader().getResource(pkg.replaceAll("\\.", "/"))).getPath();
        File file = new File(path);
        for (File listFile : file.listFiles()) {
            if (listFile.isFile()) {
                Class<?> aClass1 = Class.forName(pkg + "." + listFile.getName().replaceAll(".class", ""));
                if(aClass1.isAnnotationPresent(ParentKey.class))
                classes.add(pkg + "." + listFile.getName().replaceAll(".class", ""));
            } else {
                scan(pkg + "." + listFile.getName());
            }
        }
    }

    /**
     * 处理注解数据
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/10/21 11:47
    */
    private void getData() throws ClassNotFoundException, IOException {
        if (CollectionUtils.isEmpty(classes)) {
            return;
        }
        List<PermissionDTO> permissionDTOS = new ArrayList<>();
        for (String aClass : classes) {
            Class<?> aClass1 = Class.forName(aClass);
            if(aClass1.isAnnotationPresent(ParentKey.class)){
                ParentKey parent = aClass1.getAnnotation(ParentKey.class);
                Method[] declaredMethods = aClass1.getDeclaredMethods();
                for (Method declaredMethod : declaredMethods) {
                    if(declaredMethod.isAnnotationPresent(Auth.class)){
                        Auth annotation = declaredMethod.getAnnotation(Auth.class);
                        PermissionDTO permissionDTO = new PermissionDTO();
                        permissionDTO.setCode(parent.code()+"."+annotation.code());
                        permissionDTO.setName(annotation.name());
                        permissionDTO.setParentCode(parent.code());
                        permissionDTO.setType(PermissionDTO.defaultType);
                        permissionDTOS.add(permissionDTO);
                    }
                }
            }
        }
        //添加进json文件数据  组成树
        Permission all = PermissionJsonSerialize.getAll(permissionDTOS);
        log.info("全线数据树加载:"+ JSON.toJSONString(all));
    }
}
