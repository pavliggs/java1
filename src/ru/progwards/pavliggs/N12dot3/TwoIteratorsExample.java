package ru.progwards.pavliggs.N12dot3;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class TwoIteratorsExample {
    static final int ELEMENTS_COUNT = 5;

    public static void main(String[] args) {
        List<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < ELEMENTS_COUNT; i++) {
            linkedList.add(i + 1);
        }

        System.out.println(linkedList);

        ListIterator<Integer> iterator1 = linkedList.listIterator();
        ListIterator<Integer> iterator2 = linkedList.listIterator(linkedList.size());

        while (iterator1.hasNext() && iterator2.hasPrevious()) {
            Integer intObj1 = iterator1.next();
            Integer intObj2 = iterator2.previous();

            System.out.println("Iterator 1 вернул " + intObj1);
            System.out.println("Iterator 2 вернул " + intObj2);

            System.out.println();
        }
    }
}
