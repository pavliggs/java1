package ru.progwards.pavliggs.N8dot3test;

public class Person {

    public static class Child1 {
        String hello() {
            return "привет";
        }
    }

    public class Child2 {
        String hello() {
            return "servus";
        }
    }

    public static void main(String[] args) {
        Child1 child1 = new Child1();
        Child2 child2 = new Person().new Child2();
        System.out.println(child1.hello());
        System.out.println(child2.hello());
    }
}
