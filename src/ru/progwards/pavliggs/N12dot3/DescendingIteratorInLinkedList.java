package ru.progwards.pavliggs.N12dot3;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class DescendingIteratorInLinkedList {
    static final int ELEMENTS_COUNT = 5;

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < ELEMENTS_COUNT; i++) {
            linkedList.add(i + 1);
        }

        System.out.println(linkedList);

        Iterator<Integer> iterator1 = linkedList.iterator();
        Iterator<Integer> iterator2 = linkedList.descendingIterator();

        while (iterator1.hasNext() && iterator2.hasNext()) {
            Integer intObj1 = iterator1.next();
            Integer intObj2 = iterator2.next();

            System.out.println("Iterator 1 вернул " + intObj1);
            System.out.println("Iterator 2 вернул " + intObj2);

            System.out.println();
        }
    }
}
