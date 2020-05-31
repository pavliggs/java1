package ru.progwards.java2.lessons.basetypes;

import java.util.*;

public class MainBiDirList {
    final static int MILLION = 1000_000;

    public static void main(String[] args) {
        BiDirList<Integer> list = new BiDirList<>();

        list.addLast(5);
        list.addLast(5);
        list.addLast(9);
        list.addFirst(11);

        Integer[] arr = new Integer[MILLION];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        BiDirList<Integer> list1 = BiDirList.from(arr);

        Iterator<Integer> iterator = list1.iterator();

        long start1 = System.currentTimeMillis();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        long finish1 = System.currentTimeMillis() - start1;

        long start2 = System.currentTimeMillis();
        list1.forEach(System.out::println);
        long finish2 = System.currentTimeMillis() - start2;

        System.out.println("Время выполнения итератора - " + finish1);
        System.out.println("Время выполнения forEach - " + finish2);

        Map<Integer, String> map = new HashMap<>();
    }
}
