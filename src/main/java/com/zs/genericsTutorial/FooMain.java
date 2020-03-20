package com.zs.genericsTutorial;

import java.util.LinkedList;
import java.util.List;

/**
 * @auther: madisonzhuang
 * @date: 2020/3/16 10:46
 * @description:
 */
class Holder<T> {
    private T t;
    public Holder(T t) {
        this.t = t;
    }
    public T getValue() {
        return t;
    }
}

public class FooMain {

    private static Object newHolder() {
        return new Holder<Integer>(3);
    }

    public static void main(String args[]) {
        Holder<String> hs = (Holder<String>) newHolder(); // line-18
        String s = hs.getValue();                         // line-19

//        List<? extends Season> seasonList = new LinkedList<>();
//        seasonList.add(new Spring());
        List<? extends Season> seasonList = new LinkedList<Spring>();
//        seasonList.add(new Spring());
    }
}


class Season{
}
class  Spring extends Season{
}
