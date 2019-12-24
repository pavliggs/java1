package ru.progwards.java1.lessons.arrays;

import java.util.Arrays;

public class DIntArray {
    private int[] arr;

    public void add(int num) {
        int[] newArray = Arrays.copyOf(arr, arr.length + 1);
        newArray[newArray.length - 1] = num;
        System.out.println(Arrays.toString(newArray));
    }

    public static void main(String[] args) {
        DIntArray dIntArray = new DIntArray();
        dIntArray.add(8);
    }
}
