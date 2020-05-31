package ru.progwards.pavliggs.java2.N4dot1;

import ru.progwards.pavliggs.java2.N4dot1.EList.ListItem;

public class TestEList {
    static class Item extends ListItem {
        int item;

        Item(int item) {
            this.item = item;
        }
    }

    public static void main(String[] args) {
        EList eList = new EList();
        eList.add(new Item(3));
        eList.add(new Item(5));
        eList.add(new Item(7));

        Item current = (Item) eList.getHead();

        while (current != null) {
            System.out.println(current.item);
            current = (Item) current.getNext();
        }
    }
}
