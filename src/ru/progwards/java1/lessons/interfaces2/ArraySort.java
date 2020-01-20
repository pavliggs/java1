package ru.progwards.java1.lessons.interfaces2;

import java.util.Arrays;

public class ArraySort {

    public static void sort(Comparable<Number>[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i].compareTo((Number)a[j]) == 1) {
                    Comparable temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(a));
    }

    public static void main(String[] args) {
        Comparable[] arr = {new IntNumber(10), new IntNumber(1), new IntNumber(5)};
        System.out.println(Arrays.toString(arr));
        sort(arr);
    }
}
