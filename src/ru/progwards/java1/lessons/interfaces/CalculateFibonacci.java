package ru.progwards.java1.lessons.interfaces;

public class CalculateFibonacci {
    static CacheInfo lastFibo;

    public static int fiboNumber(int n) {
        //присвоим переменным fibo1 и fibo2 значения первых двух чисел последовательности Фибоначчи
        int fibo1 = 1;
        int fibo2 = 1;

        //инициализируем объект lastFibo, если он равен null
        if (lastFibo == null)
            lastFibo = new CacheInfo();
        if (n == lastFibo.n) {
            return lastFibo.fibo;
        } else {
            //цикл будем начинать с i = 2, т.к. значения первых двух чисел у нас уже имеются
            for (int i = 2; i < n; i++) {
                int fiboNext = fibo1 + fibo2;
                //путём присваивания сдвигаем вперёд значения fibo1 и fibo2 в последовательности Фибоначчи
                fibo1 = fibo2;
                fibo2 = fiboNext;
            }
            lastFibo.n = n;
            lastFibo.fibo = fibo2;
            return lastFibo.fibo;
        }
    }

    public static CacheInfo getLastFibo() {
        return lastFibo;
    }

    public static void clearLastFibo() {
        lastFibo = null;
    }

    public static class CacheInfo {
        public int n;
        public int fibo;

        @Override
        public String toString() {
            return "n = " + lastFibo.n + ", fibo = " + lastFibo.fibo;
        }
    }


    public static void main(String[] args) {
        System.out.println(fiboNumber(15));
    }
}
