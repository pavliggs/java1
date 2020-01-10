package ru.progwards.java1.lessons.interfaces;

import java.util.Arrays;

public class ArraySort {

    public static void sort(CompareWeight[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                //a[i] будет больше a[j] только если метод compareWeight вернет значение CompareResult.EQUAL
                if (a[i].compareWeight(a[j]) == CompareWeight.CompareResult.EQUAL) {
                    CompareWeight temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(a));
    }

    public static void main(String[] args) {
        CompareWeight[] arr = {new Cow(200), new Duck(5), new Hamster(1)};
        System.out.println(Arrays.toString(arr));
        sort(arr);
    }
}

