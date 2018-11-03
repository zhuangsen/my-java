package com.zs.java8.importnew.functionalInterface;

@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}
