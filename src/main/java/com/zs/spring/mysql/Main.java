package com.zs.spring.mysql;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @auther: madisonzhuang
 * @date: 2019-02-19 10:21
 * @description:
 */
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.getBean(Test.class).get();
    }
}
