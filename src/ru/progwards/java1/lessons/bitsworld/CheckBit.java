package ru.progwards.java1.lessons.bitsworld;

public class CheckBit {
    public static int checkBit(byte value, int bitNumber) {
        /*
        сдвигаем число вправо на значение равное порядковому номеру бита, значение которого собираемся получить
        */
        value >>= bitNumber;
        return value & 0b1;
    }

    public static void main(String[] args) {
        System.out.println(checkBit((byte)0b1001010, 3));
    }
}
