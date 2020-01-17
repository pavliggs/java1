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
        * чтобы положить value в массив необходимо получить все его элементы (цифры), которые затем будут являться
        * значениями элементов в массиве
        * нужно создать цикл и итерации будут производиться пока value будет больше 0, то есть цикл будет выполняться
        * пока результат выполнения метода value.compareTo(BigInteger.ZERO) будет равен 1, что и будет означать,
        * что value больше 0
        * */
        for (int i = value.toString().length() - 1; value.compareTo(BigInteger.ZERO) == 1; i--) {
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
        //для преобразования элементов массива в число типа BigInteger будем использовать сложение строк
        for (int i = 0; i < count; i++) {
            str = digits[count - (i + 1)] + str;
        }
        return new BigInteger(str);
    }

    boolean add(ArrayInteger num) {
        /*
        * чтобы сложить цифры массивов и записать из в массив вызвавший метод необходимо сначала определить
        * меньший по количеству цифр BigInteger - по меньшему BigInteger будут считаться итерации цикла
        * */

        // вариант когда BigInteger1 > BigInteger2
        if (count >= num.count) {
            for (int i = 0; i < num.count; i++) {
                byte res = (byte)(digits[count - (i + 1)] + num.digits[num.count - (i + 1)]);
                if (res < 10)
                    digits[count - (i + 1)] = res;
                /*
                * если сумма цифр превышает 9, то в элемент массива запишется вторая цифра результата ("13" - запишется "3"),
                * а 1 перенесется на следующий элемент
                * */
                else {
                    digits[count - (i + 1)] = (byte)(res - 10);
                    digits[count - (i + 2)] += 1;
                }
            }
            // если получившееся число больше, чем размер массива digits, то заполним массив 0
            if (toInt().toString().length() > digits.length) {
                Arrays.fill(digits, (byte)0);
                return false;
            }
        }
        // вариант когда BigInteger1 < BigInteger2
        else {
            for (int i = 0; i < count; i++) {
                byte res = (byte)(digits[count - (i + 1)] + num.digits[num.count - (i + 1)]);
                if (res < 10)
                    num.digits[num.count - (i + 1)] = res;
                else {
                    num.digits[num.count - (i + 1)] = (byte)(res - 10);
                    num.digits[num.count - (i + 2)] += 1;
                }
            }
            if (num.toInt().toString().length() > digits.length) {
                Arrays.fill(digits, (byte)0);
                return false;
            } else
                digits = Arrays.copyOf(num.digits, num.digits.length);
        }
        return true;
    }

    public static void main(String[] args) {
        ArrayInteger arr1 = new ArrayInteger(9);
        ArrayInteger arr2 = new ArrayInteger(9);
        BigInteger bigInteger1 = new BigInteger("1");
        BigInteger bigInteger2 = new BigInteger("99999999");
        arr1.fromInt(bigInteger1);
        arr2.fromInt(bigInteger2);
        System.out.println(Arrays.toString(arr1.digits));
        System.out.println(Arrays.toString(arr2.digits));
        arr1.add(arr2);
        System.out.println(Arrays.toString(arr1.digits));
    }
}
