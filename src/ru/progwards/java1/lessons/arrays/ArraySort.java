package ru.progwards.java1.lessons.arrays;

import java.util.Arrays;

public class ArraySort {

    //при помощи вложенных циклов сортируем массив от меньшего значения к большему
    public static void sort(int[] a) {
            for (int i = 0; i < a.length; i++) {
                for (int j = i + 1; j < a.length; j++) {
                    if (a[i] > a[j]) {
                        //при помощи дополнительной переменной поменяем местами элементы массива
                        int temp = a[i];
                        a[i] = a[j];
                        a[j] = temp;
                    }
                }
            }
            System.out.println(Arrays.toString(a));
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, -4, 2, 10, -7};
        sort(arr);
    }
}
