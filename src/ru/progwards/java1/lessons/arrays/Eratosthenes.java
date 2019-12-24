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
        for (int i = 2; i <= this.sieve.length - 1; i++) {
            for (int j = 2 * i; j <= this.sieve.length * 2 - 1; j += i) {
                if (j % i == 0)
                    System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        Eratosthenes era = new Eratosthenes(10);
        System.out.println(Arrays.toString(era.sieve));
    }

}
