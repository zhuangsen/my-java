package com.zs.spring.start;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @auther: madisonzhuang
 * @date: 2019-02-19 15:41
 * @description:
 */
public class Main {

    public static void main(String[] args) {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        context.register(Service.class);
//        context.refresh();//很重要
//        System.out.println(context.getBean(Service.class).getClass().getSimpleName());

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.registerBean("myService", Service.class, () -> new Service("Hello"), z -> {
            z.setScope("prototype");
        });
        context.refresh();
        System.out.println(context.getBean("myService").getClass().getSimpleName());
        System.out.println(context.getBeanDefinition("myService").getScope());

    }
}
