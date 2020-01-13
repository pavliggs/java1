package ru.progwards.java1.lessons.bigints;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigAlgebra {

    static BigDecimal fastPow(BigDecimal num, int pow) {
        if (pow == 0)
            return new BigDecimal("1");
        else {
            int x = pow / 2;
            //изначально присвоим переменной значение 1
            BigDecimal res = new BigDecimal("1");
            for (int i = 0; i < x; i++) {
                //при каждой итерации переменную res умножаем на num в квадрате
                res = res.multiply(num.multiply(num));
            }
            if (pow % 2 == 0)
                return res;
            else
                return res.multiply(num);
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
        System.out.println(fastPow(new BigDecimal("3"), 9));
    }
}
