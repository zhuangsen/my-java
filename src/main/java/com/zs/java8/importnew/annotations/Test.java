package com.zs.java8.importnew.annotations;

public class Test {

    public static void main(String[] args) {
        // 使用变体2，Java编译器能够在内部自动对@Hint进行设置。这对于通过反射来读取注解信息来说，是非常重要的。
        Hint hint = Person1.class.getAnnotation(Hint.class);
        System.out.println(hint);                   // null

        Hints hints1 = Person.class.getAnnotation(Hints.class);
        System.out.println(hints1.value().length);  // 2

        Hint[] hints2 = Person1.class.getAnnotationsByType(Hint.class);
        System.out.println(hints2.length);          // 2
    }
}
