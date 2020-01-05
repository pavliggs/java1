package ru.progwards.java1.lessons.bitsworld;

public class CheckBit {
    public static int checkBit(byte value, int bitNumber) {
        /*
        Количество итераций цикла будет меньше индекса элемента, значение которого нужно получить.
        Поэтому сдвигаем число вправо пока нужный элемент не окажется младшим битом числа
        */
        for (int i = 0; i < bitNumber; i++) {
            value >>= 1;
        }
        return value & 0b1;
    }

    public static void main(String[] args) {
        System.out.println(checkBit((byte)0b1001010, 1));
    }
}
