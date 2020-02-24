package ru.progwards.pavliggs.N14dot1;

import java.util.ArrayDeque;

public class Test {
    ArrayDeque<Integer> array2queue(int[] a) {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>(a.length);
        for (int i = 0; i < a.length; i++) {
            arrayDeque.offer(a[i]);
        }
        return arrayDeque;
    }

    public static void main(String[] args) {
        int[] arr = {45, 78, 23, 67};
        System.out.println(new Test().array2queue(arr));
    }
}
