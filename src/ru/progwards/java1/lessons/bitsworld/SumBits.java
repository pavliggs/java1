package ru.progwards.java1.lessons.bitsworld;

public class SumBits {
    public static int sumBits(byte value) {
        /*
        Необходимо определить когда нужно закончить цикл по сдвигу числа вправо.
        Для этого будет удобно воспользоваться переводом числа в строку и количество итераций цикла
        будет меньше длины строки.
        Например: 011001 -> "011001" -> length = 6

        при помощи метода String.format получаем двоичное представление числа в 8-битовом формате (8 символов - %8s),
        а при помощи метода replace заполняем пустые символы нулями
        PS: хотя в принципе не обязательно, так как нули вначале числа при подсчёте суммы нам не нужны
        */
        int valueLength = String.format("%8s", Integer.toBinaryString(value)).replace(' ', '0').length();
        int res = 0;
        for (int i = 0; i < valueLength; i++) {
            res += value & 0b1;
            value >>= 1;
        }
        if (value >= 0)
            return res;
        else
            /*
            отрицательное число выводится в формате int (32 бита) и спереди числа стоят 24 единицы, а затем
            само отрицательное число, поэтому вычитаем сумму этих единиц, которые нам не нужны при подсчёте
            */
            return res - 24;
    }

    public static void main(String[] args) {
        System.out.println(sumBits((byte)0b10000000));
    }
}
