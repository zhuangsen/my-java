package com.zs.spring.beanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @auther: madisonzhuang
 * @date: 2019-02-19 16:28
 * @description:
 */
public class MyInvationHandler implements InvocationHandler {

    private Object target;

    public MyInvationHandler(Object target){
        this.target=target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("进来了");
        Object obj = method.invoke(target, args);
        System.out.println("出去了");
        return obj;
    }
}
