package ru.progwards.pavliggs.java2.N1dot2;

import java.util.ArrayList;
import java.util.List;

public class BigHeapSize {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 125_000_000; i++)
            list.add(i);

        System.out.println("Successful!");
    }
}
