package ru.progwards.java1.lessons.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Creator {
    public static Collection<Integer> fillEven(int n) {
        List<Integer> collection = new ArrayList<>();
        for (int i = 2; i <= n * 2; i++) {
            if (i % 2 == 0)
                collection.add(i);
        }
        return collection;
    }

    public static Collection<Integer> fillOdd(int n) {
        List<Integer> collection = new ArrayList<>();
        for (int i = n * 2; i >= 1; i--) {
            if (i % 2 != 0)
                collection.add(i);
        }
        return collection;
    }

    public static Collection<Integer> fill3(int n) {
        List<Integer> collection = new ArrayList<>();
        for (int i = 0; i < n * 3; i += 3) {
            collection.add(i);
            int count = 0; // заводим счётчик для того, чтобы цикл ниже имел только 2 итерации
            for (int j = i * i; count < 2; j *= i) {
                collection.add(j);
                count++;
            }
        }
        return collection;
    }

    public static void main(String[] args) {
        System.out.println(fillEven(10));
        System.out.println(fillOdd(12));
        System.out.println(fill3(4));
    }
}
