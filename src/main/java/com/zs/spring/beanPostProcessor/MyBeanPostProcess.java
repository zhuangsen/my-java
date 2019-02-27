package com.zs.spring.beanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

/**
 * @auther: madisonzhuang
 * @date: 2019-02-19 16:29
 * @description:
 */
@Component
public class MyBeanPostProcess implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Object o = Proxy.newProxyInstance(MyBeanPostProcess.class.getClassLoader(),
                bean.getClass().getInterfaces(), new MyInvationHandler(bean));
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
