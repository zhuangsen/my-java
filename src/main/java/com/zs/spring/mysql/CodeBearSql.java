package com.zs.spring.mysql;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @auther: madisonzhuang
 * @date: 2019-02-19 10:33
 * @description:
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CodeBearSql {
    String value();
}