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
        for (int i = 2; i < this.sieve.length; i++) {
            for (int j = 2 * i; j < this.sieve.length; j += i) {
                System.out.println(i);
                System.out.println(j);
            }
        }
    }

    public static void main(String[] args) {
        Eratosthenes era = new Eratosthenes(10);
        System.out.println(Arrays.toString(era.sieve));
    }

}
