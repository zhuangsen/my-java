package com.zs.design.proxy.jdkproxy;

import com.zs.design.proxy.staticproxy.IUserDao;
import com.zs.design.proxy.staticproxy.UserDao;

/**
 * @author madison
 * @description
 * @date 2021/5/19 01:31
 */
public class Test {
    public static void main(String[] args) {
        // 目标对象
        UserDao target = new UserDao();
        // 【原始的类型 class cn.itcast.b_dynamic.UserDao】
        System.out.println(target.getClass());

        // 给目标对象，创建代理对象
        UserDao proxy = (UserDao) new ProxyFactory(target).getProxyInstance();

        // class $Proxy0   内存中动态生成的代理对象
        System.out.println(proxy.getClass());

        // 执行方法   【代理对象】
        proxy.save();
    }
}
