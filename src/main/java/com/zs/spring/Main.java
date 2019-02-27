package com.zs.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @auther: madisonzhuang
 * @date: 2019-02-19 10:21
 * @description:
 */
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.getBean(ServiceImpl.class).query();

        System.out.println(context.getBean(AppConfig.class).getClass().getSimpleName());
    }
}
