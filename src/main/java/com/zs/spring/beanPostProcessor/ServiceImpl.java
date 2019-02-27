package com.zs.spring.beanPostProcessor;

import org.springframework.stereotype.Component;

/**
 * @auther: madisonzhuang
 * @date: 2019-02-19 16:28
 * @description:
 */
@Component
public class ServiceImpl implements Service {

    @Override
    public void query() {
        System.out.println("正在查询中");
    }
}
