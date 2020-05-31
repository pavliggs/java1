package ru.progwards.pavliggs.java2.N4dot1;

public class EList {
    static class ListItem {

        private ListItem next;

        void setNext(ListItem item) {
            next = item;
        }

        ListItem getNext() {
            return next;
        }

        @Override
        public String toString() {
            return "ListItem{" +
                    "next=" + next +
                    '}';
        }
    }

    private ListItem head;
    private ListItem tail;

    ListItem getHead() {
        return head;
    }

    void add(ListItem item) {
        if (head == null) {
            head = item;
            tail = item;
        } else {
            tail.setNext(item);
            tail = item;
        }
    }
}
