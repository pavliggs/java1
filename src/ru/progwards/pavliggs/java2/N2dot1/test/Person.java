package ru.progwards.pavliggs.java2.N2dot1.test;

import java.util.Comparator;
import java.util.List;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return name + " " + age;
    }

    void sortAndPrint(List<Person> list) {
        list.sort(Comparator.comparing(o -> o.age));
        list.forEach(System.out::println);
    }

    public static void main(String[] args) {

    }
}
