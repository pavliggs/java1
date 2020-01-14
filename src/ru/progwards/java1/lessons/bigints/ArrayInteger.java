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

    boolean add(ArrayInteger num) {
        String str1 = "";
        String str2 = "";
        for (int i = 0; i < count; i++) {
            str1 += Integer.toString(digits[count - (i + 1)]);
        }
        for (int i = 0; i < num.count; i++) {
            str2 += Integer.toString(num.digits[num.count - (i + 1)]);
        }

        int res = Integer.parseInt(str1) + Integer.parseInt(str2);
        for (int i = 0; res > 0; i++) {
            digits[i] = (byte)(res % 10);
            res /= 10;
        }
        return true;
    }

    public static void main(String[] args) {
        ArrayInteger arr1 = new ArrayInteger(10);
        ArrayInteger arr2 = new ArrayInteger(5);
        BigInteger bigInteger1 = new BigInteger("12345689");
        BigInteger bigInteger2 = new BigInteger("569");
        System.out.println(Arrays.toString(arr1.digits));
        System.out.println(Arrays.toString(arr2.digits));
        arr1.fromInt(bigInteger1);
        arr2.fromInt(bigInteger2);
        System.out.println(Arrays.toString(arr1.digits));
        System.out.println(Arrays.toString(arr2.digits));
        System.out.println(arr1.toInt());
        System.out.println(arr2.toInt());
        arr1.add(arr2);
        System.out.println(Arrays.toString(arr1.digits));
    }
}
