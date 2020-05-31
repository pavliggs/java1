package ru.progwards.pavliggs.java2.N4dot2;

import java.util.LinkedList;
import java.util.List;

public class TestCHashTable {
    public static void main(String[] args) {
        CHashTable<String> table = new CHashTable<>(17);
        for (int i = 0; i < 100; i++) {
            table.add(i, "i = " + i);
        }

        for (int i = 0; i < 100; i++) {
            System.out.println(table.get(i));
        }
    }
}
