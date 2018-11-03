package com.zs.java8.importnew.functionalInterface;

interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}