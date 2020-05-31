package ru.progwards.pavliggs.java2.N4dot1;

public class TestOList {
    public static void main(String[] args) {
        OList<Integer> list = new OList<>();
        list.add(3);
        list.add(17);
        list.add(7);
        list.add(10);
        list.add(11);
        list.add(21);
        OList<Integer>.ListItem<Integer> current = list.getHead();
        System.out.println(list.getHead());
//        System.out.println(list.tail);
        while (current != null) {
            System.out.println(current.getItem());
            current = current.getNext();
        }
    }
}
