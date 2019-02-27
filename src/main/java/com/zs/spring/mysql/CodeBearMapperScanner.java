package com.zs.spring.mysql;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @auther: madisonzhuang
 * @date: 2019-02-19 10:30
 * @description:
 */
@Import(CodeBearMapperScannerRegistrar.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface CodeBearMapperScanner {
    String value();
}
