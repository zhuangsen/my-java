package com.zs.spring.beanPostProcessor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @auther: madisonzhuang
 * @date: 2019-02-19 16:30
 * @description:
 */
public class Main {

    public static void main(String[] args) {
//        MyBeanPostProcess
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        context.getBean(Service.class).query();


        //MyBeanFactoryPostProcessor
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        System.out.println(context.getBeanDefinition("repo").getScope());
    }
}
