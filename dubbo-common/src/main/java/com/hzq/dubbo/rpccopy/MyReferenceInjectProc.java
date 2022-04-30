package com.hzq.dubbo.rpccopy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Huangzq
 * @Date: 2022/4/30 17:32
 */
@Component
public class MyReferenceInjectProc implements ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware, BeanFactoryPostProcessor {

    public static ApplicationContext applicationContext;

    public static List<Class<?>> pkgScan() throws ClassNotFoundException {
        String path = "com.hzq.dubbo";
        String url = path.replaceAll("\\.","/");
        URL resource = MyReferenceInjectProc.class.getClassLoader().getResource(url);

        File file = new File(resource.getFile());
        List<String> paths = new ArrayList<>();
        getClasses(file,paths);

        List<Class<?>> classes = new ArrayList<>();
        for (String s : paths) {
            String classString = s.substring(file.getPath().length())
                    .replaceAll("\\\\", ".")
                    .replaceAll(".class", "");

            Class<?> aClass = Class.forName(path+classString);
            classes.add(aClass);
        }
        return classes;
    }

    public static void getClasses(File file,List<String> res){
        if(file.isFile()){
            res.add(file.getPath());
        }

        File[] files = file.listFiles();

        if(null != files && files.length>0){
            for (File file1 : files) {
                getClasses(file1,res);
            }
        }

    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("spring 初始化完成");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("spring 工厂扫包及注入开始====>");
        try {
            //
            List<Class<?>> classes = pkgScan();
            for (Class<?> aClass : classes) {
                Field[] declaredFields = aClass.getDeclaredFields();
                for (Field declaredField : declaredFields) {
                    MyReference declaredAnnotation = declaredField.getDeclaredAnnotation(MyReference.class);
                    if(null != declaredAnnotation){
                        Class<?> aClass1 = declaredField.getType();
                        RpcInvocationHandler handler = new RpcInvocationHandler(declaredAnnotation.host(),declaredAnnotation.port());

                        Object o = Proxy.newProxyInstance(aClass1.getClassLoader(), new Class[]{aClass1}, handler);
                        configurableListableBeanFactory.registerSingleton(aClass1.getName(),o);
                        System.out.println("MyReference 示例注入："+ aClass1.getSimpleName() + ",注入对象："+o.getClass().getName());
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("spring 工厂扫包及注入完成====>");
    }
}
