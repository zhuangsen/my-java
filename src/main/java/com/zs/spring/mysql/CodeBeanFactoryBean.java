package com.zs.spring.mysql;


import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @auther: madisonzhuang
 * @date: 2019-02-19 10:32
 * @description:
 */
public class CodeBeanFactoryBean implements FactoryBean, InvocationHandler {

    private Class clazz;

    public CodeBeanFactoryBean(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        CodeBearSql annotation = method.getAnnotation(CodeBearSql.class);
        String sql= annotation.value();
        System.out.println(sql);
        return sql;
    }

    @Override
    public Object getObject() throws Exception {
        Object o = Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{clazz}, this);
        return o;
    }

    @Override
    public Class<?> getObjectType() {
        return clazz;
    }
}
