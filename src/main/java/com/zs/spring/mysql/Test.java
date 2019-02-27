package com.zs.spring.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther: madisonzhuang
 * @date: 2019-02-19 10:37
 * @description:
 */
@Service
public class Test {

    @Autowired
    UserRepo userRepo;

    public  void get(){
        userRepo.get();
    }
}
