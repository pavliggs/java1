package ru.progwards.java1.lessons.bigints;

import java.math.BigInteger;
import java.util.Arrays;

public class ArrayInteger {
    byte[] digits;
    int count = 0;

    ArrayInteger(int n) {
        this.digits = new byte[n];
    }

    void fromInt(BigInteger value) {
        /*
        * чтобы положить value в массив необходимо получить все его элементы (цифры), которые затем будут являтся
        * значениями элементов в массиве
        * нужно создать цикл и итерации будут производиться пока value будет больше 0, то есть цикл будет выполняться
        * пока результат выполнения метода value.compareTo(BigInteger.ZERO) будет равен 1, что и будет означать,
        * что value больше 0
        * */
        for (int i = 0; value.compareTo(BigInteger.ZERO) == 1; i++) {
            //операцией остаток от деления получаем последнюю цифру и присваиваем её нулевому элементу массива digits
            digits[i] = value.mod(BigInteger.TEN).byteValue();
            /*
             * смещаем value вправо путем деления на 10 что в итоге приведет к тому что предпоследняя цифра станет
             * последней и повторяем итерации
             * */
            value = value.divide(BigInteger.TEN);
            //переменная count содержит число равное количеству итераций цикла (равно количеству цифр в переменной value)
            count++;
        }
    }

    BigInteger toInt() {
        String str = "";
        /*
        * для преобразования элементов массива в число типа BigInteger будем использовать сложение строк
        * каждое значение элемента массива будет преобразовываться в строку и складываться со следующим элементом массива
        * */
        for (int i = 0; i < count; i++) {
            str += Integer.toString(digits[count - (i + 1)]);
        }
        return new BigInteger(str);
    }

    public static void main(String[] args) {
        ArrayInteger arr = new ArrayInteger(10);
        BigInteger bigInteger = new BigInteger("12345689");
        System.out.println(Arrays.toString(arr.digits));
        arr.fromInt(bigInteger);
        System.out.println(Arrays.toString(arr.digits));
        System.out.println(arr.toInt());
    }
}
