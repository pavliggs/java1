package ru.progwards.java1.lessons.test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Test1 {
    public static void main(String[] args) {
        System.out.println("Сделаю коммит, запушу в репо: робот, проверяй теперь всё это...");

        char char1 = 'В';
        System.out.println((int)char1);

        char[] chArr = new char[65536];
        chArr[1042] = 'В';
        chArr[1041] = '!';
        char char2 = chArr[(int)char1];
        System.out.println(char2);
        System.out.println(char1 + char2);
        System.out.println('a' + 'a');
        System.out.println();

        int a = 0b01;
        int b = 0b10;
        int c = a | b;
        System.out.println(Integer.toBinaryString(c));

        c = c & ~a;
        System.out.println(Integer.toBinaryString(c));
        c = c & ~b;
        System.out.println(Integer.toBinaryString(c));

        Collection<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            numbers.add(i);
        }

        ((ArrayList)numbers).add(3, 5);
    }
}
