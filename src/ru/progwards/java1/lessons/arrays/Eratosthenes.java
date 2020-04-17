package ru.progwards.java1.lessons.arrays;

import java.util.Arrays;

public class Eratosthenes {
    private boolean[] sieve;

    public Eratosthenes(int N) {
        sieve = new boolean[N];
        Arrays.fill(sieve, true);
        sift();
    }

    private void sift() {
        /*
        чтобы не повторялись уже просеянные числа - числа можно зачеркивать начиная сразу с числа i^2,
        потому что все составные числа меньше него уже будут зачеркнуты к этому времени
        (реализация займет меньше памяти). Метод подсмотрел в ГУГЛЕ :)
        for (int i = 2; i * i < sieve.length; i++) - внешний цикл
        for (int j = i * i; j < sieve.length; j += i) - внутренний цикл
        */
        for (int i = 2; i < sieve.length; i++) {
            if (sieve[i]) {
                for (int j = 2 * i; j < sieve.length; j += i) {
                    sieve[j] = false;
                }
            }
        }
    }

    /*
    передаем число, по которому хотим узнать простое оно или сложное
    и возвращаем элемент массива с индексом равным этому числу
    */
    public boolean isSimple(int n) {
        return sieve[n];
    }

    public static void main(String[] args) {
        Eratosthenes era = new Eratosthenes(20);
        System.out.println(Arrays.toString(era.sieve));
        System.out.println(era.isSimple(18));
    }

}
