package ru.progwards.java1.lessons.arrays;

import java.util.Arrays;

public class DIntArray {
    private int[] originalArray;

    public DIntArray() {
        originalArray = new int[0];
    }

    public void add(int num) {
        int[] newArray = Arrays.copyOf(originalArray, originalArray.length + 1);
        //при каждом вызове метода новый элемент будет добавляться в конец массива
        newArray[newArray.length - 1] = num;
        originalArray = newArray;
        System.out.println(Arrays.toString(originalArray));
    }

    public void atInsert(int pos, int num) {
        //заведём условие, которое ограничит параметр pos длинной конечного массива
        if (pos <= originalArray.length) {
            int[] newArray = new int[originalArray.length + 1];
            //копируем элементы из старого массива в новый до позиции, которую будем добавлять
            System.arraycopy(originalArray, 0, newArray, 0, pos);
            newArray[pos] = num;
            /*
            копируем оставшиеся элементы из старого массива в новый с позиции, которая идет
            после добавленной позиции до конца
            */
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
            //копируем элементы из старого массива в новый до позиции, которую будем добавлять
            System.arraycopy(originalArray, 0, newArray, 0, pos);
            /*
            копируем оставшиеся элементы из старого массива в новый с позиции, которая идет
            после удаленной позиции до конца
            */
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
        dIntArray.add(800);
        dIntArray.add(23);
        dIntArray.add(13);
        dIntArray.add(10);
        dIntArray.add(78);
        dIntArray.add(-300);
        dIntArray.atInsert(3, 400);
        dIntArray.at(6);
        dIntArray.atDelete(6);
        dIntArray.at(6);
        dIntArray.at(1);
    }
}
