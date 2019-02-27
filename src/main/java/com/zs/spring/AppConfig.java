package com.zs.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @auther: madisonzhuang
 * @date: 2019-02-19 10:19
 * @description:
 */
@Configuration
//@ComponentScan
public class AppConfig {
    @Bean
    public ServiceImpl getServiceImpl() {
        return new ServiceImpl();
    }

    @Bean
    public OtherImpl getOtherImpl() {
        getServiceImpl();
        return new OtherImpl();
    }
}
