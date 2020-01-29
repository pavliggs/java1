package ru.progwards.pavliggs.N11dot3;

import java.util.Arrays;

public class NormalizeRegNums {
    public static void main(String[] args) {
        String[] regNums = {"b200AC  56", "№ C999cc78", "k  677 aa34", "e 1 2 1 o t", "  ##T  457 # k # m"};

        for (int i = 0; i < regNums.length; i++) {
            // приводим строку к верхнему регистру
            regNums[i] = regNums[i].toUpperCase();
            // убираем все пробелы
            regNums[i] = regNums[i].replace(" ", "");
            // убираем "№"
            regNums[i] = regNums[i].replace("№", "");
        }

        System.out.println(Arrays.toString(regNums));
    }
}
