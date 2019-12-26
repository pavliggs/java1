package ru.progwards.java1.lessons.arrays;

import java.util.Arrays;

public class DIntArray {
    private int[] originalArray;

    public void add(int num) {
//        originalArray = new int[10];
        int[] newArray = new int[originalArray.length + 1];
//        int[] newArray = Arrays.copyOf(originalArray, originalArray.length + 1);
        newArray = Arrays.copyOf(originalArray, newArray.length);
        newArray[newArray.length - 1] = num;
        originalArray = newArray;
        System.out.println(Arrays.toString(originalArray));
    }

    public void atInsert(int pos, int num) {
        //заведём условие, которое ограничит параметр pos длинной конечного массива
        if (pos <= originalArray.length) {
            int[] newArray = new int[originalArray.length + 1];
            System.arraycopy(originalArray, 0, newArray, 0, pos);
            newArray[pos] = num;
            System.arraycopy(originalArray, pos, newArray, pos + 1, originalArray.length - pos);
            originalArray = newArray;
            System.out.println(Arrays.toString(originalArray));
        } else
            System.out.println("Элемента с индексом pos = " + pos + " в данном массиве не существует");
    }

    public void atDelete(int pos) {
        //заведём условие, которое ограничит параметр pos длинной массива
        if (pos < originalArray.length) {
            int[] newArray = new int[originalArray.length - 1];
            System.arraycopy(originalArray, 0, newArray, 0, pos);
            System.arraycopy(originalArray, pos + 1, newArray, pos, newArray.length - pos);
            originalArray = newArray;
            System.out.println(Arrays.toString(originalArray));
        } else
            System.out.println("Элемента с индексом pos = " + pos + " в данном массиве не существует");
    }

    public int at(int pos) {
        if (pos < originalArray.length) {
            System.out.println(originalArray[pos]);
            return originalArray[pos];
        } else
            System.out.println("Элемента с индексом pos = " + pos + " в данном массиве не существует");
            return 0;
    }

    public static void main(String[] args) {
        DIntArray dIntArray = new DIntArray();
        dIntArray.add(456);
//        dIntArray.atInsert(8, 799);
//        dIntArray.atDelete(7);
        dIntArray.at(10);
    }
}
