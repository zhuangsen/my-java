package com.zs.java8.importnew.interfacedefault;

public class Test {

    public static void main(String[] args) {
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };

        System.out.println(formula.calculate(100));     // 100.0
        System.out.println(formula.sqrt(16));           // 4.0

        // 访问默认接口方法
        // 默认方法无法在lambda表达式内部被访问。因此下面的代码是无法通过编译的：
//        Formula formula = (a) -> sqrt( a * 100);
    }
}
