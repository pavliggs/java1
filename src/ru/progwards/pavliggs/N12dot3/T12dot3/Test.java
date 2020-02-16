package ru.progwards.pavliggs.N12dot3.T12dot3;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Test {
    public void iterator3(ListIterator<Integer> iterator) {
        while (iterator.hasNext()) {
            Integer intObj = iterator.next();
            if (intObj % 3 == 0)
                iterator.set(iterator.nextIndex() - 1);
        }
    }

    public static void main(String[] args) {
        List<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            linkedList.add((int)Math.floor(Math.random() * 100));
        }

        System.out.println(linkedList);

        ListIterator<Integer> iterator = linkedList.listIterator();
        new Test().iterator3(iterator);

        System.out.println(linkedList);
    }
}
