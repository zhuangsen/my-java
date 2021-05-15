package com.zs.java15.base;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author madison
 * @description
 * @date 2021/5/15 01:03
 */
public class Demo4 implements Cloneable{
    private String desc;

    private String test() {
        System.out.println("testee");
        return "test1";
    }

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, CloneNotSupportedException {
        // 反射的实现方式：
        // 第一步：获取Class对象，有4中方法：
        // 1）Class.forName(“类的路径”)； 2）类名.class 3）对象名.getClass() 4）基本类型的包装类，可以调用包装类的Type属性来获得该包装类的Class对象
        System.out.println(Class.forName("com.zs.java15.base.Demo4"));
        System.out.println(Demo4.class);
        Demo4 demo4 = new Demo4();
        System.out.println(demo4.getClass());
        System.out.println(Integer.TYPE);
        System.out.println("----------");

        Class clazz = Demo4.class;
        Field[] fields = clazz.getDeclaredFields();
        Method[] methods = clazz.getDeclaredMethods();
        for (Field field : fields) {
            System.out.println(field.getName() + "\t" + field.getType());
        }
        for (Method method : methods) {
            System.out.println(method.getName() + "\t" + method.getReturnType() + "\t" + method.getParameterCount() + "\t");
            Arrays.stream(method.getParameterTypes()).forEach(System.out::println);
            if (method.getName() != "main") {
                if (method.getParameterCount() > 0) {
                    System.out.println(method.invoke(demo4, "234"));
                } else {
                    System.out.println(method.invoke(demo4));
                }
            }


            System.out.println("======");
        }


        // 创建对象的几种方法
        // 1.使用 new 关键字
        Demo4 demo41 = new Demo4();
        // 2.使用反射方式创建对象，使用 newInstance()，但是得处理两个异常 InstantiationException、IllegalAccessException：
        // @Deprecated(since="9")
        Demo4 demo42 = Demo4.class.newInstance();
        Demo4 demo43 = Demo4.class.getConstructor().newInstance();
        // 3.使用 clone 方法
        // 保护方法，实现对象的浅复制，只有实现了 Cloneable 接口才可以调用该方法，否则抛出
        // CloneNotSupportedException 异常，深拷贝也需要实现 Cloneable，同时其成员变量为引用类型
        // 的也需要实现 Cloneable，然后重写 clone 方法。
        Demo4 demo44 = (Demo4) demo41.clone();
        System.out.println(demo42.test());
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
