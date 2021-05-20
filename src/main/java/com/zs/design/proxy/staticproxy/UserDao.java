package com.zs.design.proxy.staticproxy;

/**
 * @author madison
 * @description
 * @date 2021/5/19 01:21
 */
public class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println("----已经保存数据!----");
    }
}
