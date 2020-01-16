package ru.progwards.java1.lessons.bigints;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigAlgebra {

    static BigDecimal fastPow(BigDecimal num, int pow) {
        if (pow == 0)
            return num;
        else {
            //воспользуемся алгоритмом «справа налево» из Википедии и реализуем его
            BigDecimal res = BigDecimal.ONE;
            //цикл будет работать пока pow > 0, а в конце цикла будем сдвигать pow вправо на 1
            while (pow > 0) {
                if ((pow & 1) == 1) {
                    res = res.multiply(num);
                }
                num = num.multiply(num);
                pow >>= 1;
            }
            return res;
        }
    }

    static BigInteger fibonacci(int n) {
        BigInteger fibo1 = BigInteger.ONE;
        BigInteger fibo2 = BigInteger.ONE;

        for (int i = 2; i < n; i++) {
            BigInteger fiboNext = fibo1.add(fibo2);
            fibo1 = fibo2;
            fibo2 = fiboNext;
        }
        return fibo2;
    }

    public static void main(String[] args) {
        System.out.println(fastPow(new BigDecimal("21"), 13));
    }
}
