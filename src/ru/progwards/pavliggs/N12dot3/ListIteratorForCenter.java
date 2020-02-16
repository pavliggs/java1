package ru.progwards.pavliggs.N12dot3;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class ListIteratorForCenter {
    static final int ELEMENTS_COUNT = 250_000;

    public static void main(String[] args) {
        List<Integer> linkedList = new LinkedList<>();
        ListIterator<Integer> listIterator = linkedList.listIterator();

        for (int i = 0; linkedList.size() < ELEMENTS_COUNT; i++) {
            if (listIterator.previousIndex() >= linkedList.size() / 2)
                listIterator.previous();
            listIterator.add(i);
        }
    }
}
