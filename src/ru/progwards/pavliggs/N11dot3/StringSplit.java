package ru.progwards.pavliggs.N11dot3;

import java.util.Arrays;

public class StringSplit {
    public static void main(String[] args) {
        String str = "Слово1,Слово2,Слово3,Слово4,Слово5";
        String[] strArr = str.split(",");
        System.out.println("Длина массива: " + strArr.length);
        System.out.println("Массив: " + Arrays.toString(strArr));
    }
}
