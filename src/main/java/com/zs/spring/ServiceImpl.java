package com.zs.spring;

import org.springframework.stereotype.Component;

/**
 * @auther: madisonzhuang
 * @date: 2019-02-19 10:21
 * @description:
 */
@Component
public class ServiceImpl {
    public ServiceImpl() {
        System.out.println("ServiceImpl类的构造方法");
    }

    public void query() {
        System.out.println("正在查询中");
    }
}
