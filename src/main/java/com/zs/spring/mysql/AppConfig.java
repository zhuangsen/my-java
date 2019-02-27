package com.zs.spring.mysql;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @auther: madisonzhuang
 * @date: 2019-02-19 10:36
 * @description:
 */
@Configuration
@CodeBearMapperScanner("com.zs.spring.mysql")
@ComponentScan
public class AppConfig {
}
