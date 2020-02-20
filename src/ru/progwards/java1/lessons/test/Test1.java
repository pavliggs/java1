package ru.progwards.java1.lessons.test;

import java.lang.reflect.Array;
import java.util.*;

public class Test1 {
    public static void main(String[] args) {
//        System.out.println("Сделаю коммит, запушу в репо: робот, проверяй теперь всё это...");
//
//        char char1 = 'В';
//        System.out.println((int)char1);
//
//        char[] chArr = new char[65536];
//        chArr[1042] = 'В';
//        chArr[1041] = '!';
//        char char2 = chArr[(int)char1];
//        System.out.println(char2);
//        System.out.println(char1 + char2);
//        System.out.println('a' + 'a');
//        System.out.println();
//
//        int a = 0b01;
//        int b = 0b10;
//        int c = a | b;
//        System.out.println(Integer.toBinaryString(c));
//
//        c = c & ~a;
//        System.out.println(Integer.toBinaryString(c));
//        c = c & ~b;
//        System.out.println(Integer.toBinaryString(c));
//
//        Collection<Integer> numbers = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            numbers.add(i);
//        }
//
//        ((ArrayList)numbers).add(3, 5);

//        Set<Integer> integerSet = Set.of(1, 3 , 5, 7);
//        System.out.println(integerSet);
//        TreeSet<Integer> sortedSet = new TreeSet<>(integerSet);
//        System.out.println(sortedSet);
//        System.out.println(sortedSet.first());


        Set<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        set1.add(31);
        set1.add(43);

        Set<Integer> set2 = new HashSet<>();
        set2.add(3);
        set2.add(4);
        set2.add(5);
        set2.add(6);

        Set<Integer> res = new HashSet<>(set1);
        res.removeAll(set2);
        System.out.println(res);
    }
}
