package ru.progwards.java1.lessons.maps;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class FiboMapCache {
    private Map<Integer, BigDecimal> fiboCache;
    boolean cacheOn;

    public FiboMapCache(boolean cacheOn) {
        this.cacheOn = cacheOn;
        fiboCache = new HashMap<>();
    }

    // метод возвращает n-ое число Фибоначчи типа данных BigDecimal
    public BigDecimal resultFibo(int n) {
        BigDecimal fibo1 = new BigDecimal(1);
        BigDecimal fibo2 = new BigDecimal(1);
        for (int i = 2; i < n; i++) {
            BigDecimal fiboNext = fibo1.add(fibo2);
            fibo1 = fibo2;
            fibo2 = fiboNext;
        }
        return fibo2;
    }

    public BigDecimal fiboNumber(int n) {
        try {
            if (cacheOn) {
                // если занчение с таким ключом уже имеется, то получим его
                if (fiboCache.containsKey(n))
                    return fiboCache.get(n);
                // если значения с таким ключом нет, то добавим в fiboCache рассчитанное число Фибоначчи
                fiboCache.put(n, resultFibo(n));
                return fiboCache.get(n);
            }
        } catch (Throwable t) {
            System.out.println(t);
        }
        return resultFibo(n);
    }

    public void clearCahe() {
        fiboCache = null;
    }

    public static void test() {
        FiboMapCache fiboMapCache1 = new FiboMapCache(true);
        FiboMapCache fiboMapCache2 = new FiboMapCache(false);

        long start = System.currentTimeMillis();
        for (int n = 1; n <= 1000; n++) {
            fiboMapCache1.fiboNumber(n);
        }
        long finish1 = System.currentTimeMillis() - start;

        start = System.currentTimeMillis();
        for (int n = 1; n <= 1000; n++) {
            fiboMapCache2.fiboNumber(n);
        }
        long finish2 = System.currentTimeMillis() - start;

        System.out.println("fiboNumber cacheOn = " + fiboMapCache1.cacheOn + " время выполнения " +
                finish1 + " мсек");

        System.out.println("fiboNumber cacheOn = " + fiboMapCache2.cacheOn + " время выполнения " +
                finish2 + " мсек");
    }

    public static void main(String[] args) {
//        FiboMapCache fiboMapCache = new FiboMapCache(true);
//        System.out.println(fiboMapCache.fiboNumber(100));
//        System.out.println(fiboMapCache.fiboNumber(50));
//        System.out.println(fiboMapCache.fiboNumber(25));
//        System.out.println(fiboMapCache.fiboNumber(12));
//        System.out.println(fiboMapCache.fiboNumber(6));
//        System.out.println(fiboMapCache.fiboNumber(3));
//        System.out.println(fiboMapCache.fiboNumber(1));
//
//        System.out.println(fiboMapCache.fiboNumber(3));
//
//        System.out.println(fiboMapCache.fiboCache);
//
//        fiboMapCache.clearCahe();
//
//        System.out.println(fiboMapCache.fiboCache);

        test();
    }
}
