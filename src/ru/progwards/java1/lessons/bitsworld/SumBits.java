package ru.progwards.java1.lessons.bitsworld;

public class SumBits {
    public static int sumBits(byte value) {
        /*
        Необходимо определить когда нужно закончить цикл по сдвигу числа вправо.
        Для этого будет удобно воспользоваться переводом числа в строку и количество итераций цикла
        будет меньше длины строки.
        Например: 011001 -> "11001" -> length = 5
        */
        int valueLength = Integer.toBinaryString(value).length();
        int res = 0;
        for (int i = 0; i < valueLength; i++) {
            res += value & 0b1;
            value >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(sumBits((byte)0b1110));
    }
}
