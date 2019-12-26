package ru.progwards.pavliggs;

import java.util.Arrays;

public class ArraysTest {
    //возвращает массив в обратном порядке
    public static int[] reverse(int[] originalArray) {
        int[] reversedArray = new int[originalArray.length];
        for (int i = 0; i < originalArray.length; i++) {
            reversedArray[originalArray.length - 1 - i] = originalArray[i];
        }
        return reversedArray;
    }

    public static void printArray(int[] intArray) {
        for (int i = 0; i < intArray.length; i++) {
            System.out.println("Значение элемента " + i + " = " + intArray[i]);
        }
        System.out.println();
    }

    //функция проверяет соответствуют ли значения элементов в массиве почледовательности Фибоначчи
    public static boolean checkFibo(int[] intArray) {
        for (int i = 2; i < intArray.length; i++) {
            if (intArray[i - 2] + intArray[i - 1] != intArray[i])
                return false;
        }
        return true;
    }

    //суммирует все значения элементов массива
    public static int sumArrayItems(int[] a) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        return sum;
    }

    //возвращает максимальное значение элемента в массиве
    public static int arrayMax(int[] a) {
        if (a.length == 0)
            return 0;
        else {
            Arrays.sort(a);
            return a[a.length - 1];
        }
    }

    public static void main(String[] args) {
        int[] simplelArray1 = {1, 1, 2, 3, 5, 8, 13};
        int[] simplelArray2 = reverse(simplelArray1);
        int[] simpleArray3 = new int[5];

        int[][] simple2DArray = {simplelArray1, simplelArray2, simpleArray3};

        printArray(simplelArray1);
        printArray(simplelArray2);

        System.out.println(checkFibo(simplelArray1));

        for (int i = 0; i < simple2DArray.length; i++) {
            System.out.println("checkFibo " + i + " = " + checkFibo(simple2DArray[i]));
        }

        int[] simpleArray4 = {1, 2, 5, 6};
        System.out.println(sumArrayItems(simpleArray4));

        //статическая функция Arrays.toString(), которая выводит значения элементов массива на консоль в удобной форме
        byte[] byteArray = {0, 2, 100, 7};
        double[] doubleArray = {0.25, 1.2e7, Math.PI, 35.25866};
        String[] stringArray = {"Я", "будущий", "программист"};

        System.out.println(Arrays.toString(byteArray));
        System.out.println(Arrays.toString(stringArray));

        int[] array5 = {};
        System.out.println(arrayMax(array5));


    }
}
