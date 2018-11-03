package com.zs.java8.importnew.annotations;

import java.lang.annotation.Repeatable;

@interface Hints {
    Hint[] value();
}

@Repeatable(Hints.class)
@interface Hint {
    String value();
}

// 变体1：使用注解容器（老方法）
@Hints({@Hint("hint1"), @Hint("hint2")})
class Person {
}

// 变体2：使用可重复注解（新方法）
@Hint("hint1")
@Hint("hint2")
class Person1 {}
