package com.zs.design.proxy.cglibproxy;

/**
 * @author madison
 * @description
 * @date 2021/5/19 14:29
 */
public class Test {
    public static void main(String[] args) {
        //目标对象
        UserDao target = new UserDao();

        //代理对象
        UserDao proxy = (UserDao) new ProxyFactory(target).getProxyInstance();

        //执行代理对象的方法
        proxy.save();
    }
}
