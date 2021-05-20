package com.zs.design.proxy.staticproxy;

/**
 * @author madison
 * @description
 * @date 2021/5/19 01:24
 */
public class Test {
    public static void main(String[] args) {
        // 目标对象
        UserDao target = new UserDao();
        System.out.println(target.getClass());

        //代理对象,把目标对象传给代理对象,建立代理关系
        UserDaoProxy proxy = new UserDaoProxy(target);
        System.out.println(proxy.getClass());

        //执行代理对象的方法
        proxy.save();
    }
}
