package ru.progwards.java2.lessons.generics;

import java.util.Arrays;

public class ArraySort {

    public static<T extends Comparable<T>> void sort(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i].compareTo(arr[j]) > 0) {
                    T tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {10, 3, -7, 19, 12, 0};
        sort(arr);
        System.out.println(Arrays.toString(arr));

        String[] arrStr = {"Yan", "Refactoring", "FullTime", "Sort"};
        sort(arrStr);
        System.out.println(Arrays.toString(arrStr));
    }
}
