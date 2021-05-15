package com.zs.java15.base;

import java.util.HashSet;
import java.util.Objects;

/**
 * @author madison
 * @description
 * @date 2021/5/14 23:07
 */
public class Demo2 {

    public static void main(String[] args) {
        HashSet set1 = new HashSet();
        set1.add("1");
        set1.add("1");

        for (Object a : set1) {
            System.out.println(a);
        }

        HashSet set2 = new HashSet();
        Person p1 = new Person("1");
        Person p2 = new Person("1");
        set2.add(p1);
        set2.add(p2);

        for (Object a : set2) {
            System.out.println(a);
        }
    }


    static class Person {
        private String age;

        Person(String age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" + "age='" + age + '\'' + '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            System.out.println(getClass());
            System.out.println(o.getClass());
//            if (o == null || getClass() != o.getClass()) {
            if (o == null || !(o instanceof Person)) {
                return false;
            }
            Person person = (Person) o;
            return age.equals(person.age);
        }

        @Override
        public int hashCode() {
            return Objects.hash(age);
        }
    }
}
