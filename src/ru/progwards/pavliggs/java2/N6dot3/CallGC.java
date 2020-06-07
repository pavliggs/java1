package ru.progwards.pavliggs.java2.N6dot3;

import java.util.ArrayList;
import java.util.List;

public class CallGC {

    public static void infoMemory(String info) {
        Runtime rt = Runtime.getRuntime();
        System.out.println(info);
        System.out.println("всего : " + rt.totalMemory());
        System.out.println("максимально : " + rt.maxMemory());
        System.out.println("свободно : " + rt.freeMemory());
        System.out.println("--------------------------");
    }

    public static void listFilling(List<Integer> list) {
        for (int i = 0; i < 105_000_000; i++) {
            list.add(i);
        }
    }

    public static void main(String[] args) {
        infoMemory("Память изначально:");

        List<Integer> list = new ArrayList<>();
        listFilling(list);
        infoMemory("Память после создания объектов:");

        list.clear();
        System.gc();

        infoMemory("Память после очистки памяти:");
    }
}
