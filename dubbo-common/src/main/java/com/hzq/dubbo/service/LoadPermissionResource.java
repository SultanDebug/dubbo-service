/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.service;

import com.hzq.dubbo.annotation.Auth;
import com.hzq.dubbo.annotation.ParentKey;
import com.hzq.dubbo.dto.PermissionDTO;
import com.sun.org.apache.bcel.internal.generic.ATHROW;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/10/20 16:56
 */
@Order
@Component
public class LoadPermissionResource implements CommandLineRunner {

    private List<String> classes = new ArrayList<>();

    @Override
    public void run(String... args) throws Exception {
        String pkg = "com.hzq.dubbo.bussiness.controller";

        scan(pkg);
        getData();

    }

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

    private void getData() throws ClassNotFoundException {
        if (CollectionUtils.isEmpty(classes)) {
            return;
        }
        List<PermissionDTO> permissionDTOS = new ArrayList<>();
        for (String aClass : classes) {
            Class<?> aClass1 = Class.forName(aClass);
            if(aClass1.isAnnotationPresent(ParentKey.class)){
                ParentKey parent = aClass1.getAnnotation(ParentKey.class);
                Auth[] auths = aClass1.getDeclaredAnnotationsByType(Auth.class);
                if(auths!=null && auths.length>0){
                    for (Auth auth : auths) {
                        PermissionDTO permissionDTO = new PermissionDTO();
                        permissionDTO.setCode(parent.code()+"."+auth.code());
                        permissionDTO.setName(auth.name());
                        permissionDTO.setParentCode(parent.code());
                        permissionDTO.setType(PermissionDTO.defaultType);
                        permissionDTOS.add(permissionDTO);
                    }
                }
            }
        }
    }
}
