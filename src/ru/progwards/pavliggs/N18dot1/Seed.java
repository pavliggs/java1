package ru.progwards.pavliggs.N18dot1;

import java.util.Random;

public class Seed {
    public static void main(String[] args) {
        final long SEED = 76593753963343L;

        Random random = new Random(SEED);
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextLong());
        }

        random.setSeed(SEED);
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextLong());
        }
    }
}
