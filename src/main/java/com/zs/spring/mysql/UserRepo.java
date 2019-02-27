package com.zs.spring.mysql;

/**
 * @auther: madisonzhuang
 * @date: 2019-02-19 10:36
 * @description:
 */
public interface UserRepo {
    @CodeBearSql(value = "select * from user")
    void get();
}
