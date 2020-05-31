package ru.progwards.pavliggs.java2.N4dot2;

import com.google.inject.internal.cglib.core.internal.$CustomizerRegistry;

public class CHashTable<T> {
    class CHashItem<T> {

        private T item;
        private int key;
        private CHashItem<T> next;

        CHashItem(int key, T item) {
            this.key = key;
            this.item = item;
        }

        int getKey() {
            return key;
        }

        T getItem() {
            return item;
        }

        void setNext(CHashItem<T> item) {
            next = item;
        }

        CHashItem<T> getNext() {
            return next;
        }
    }

    CHashItem[] table;

    CHashTable(int n) {
        table = new CHashItem[n];
    }

    public int getHash(int key) {
        return key % table.length;
    }

    void add(int key, T item) {
        int indx = getHash(key);
        CHashItem<T> li = new CHashItem<>(key, item);
        CHashItem<T> head = table[indx];
        table[indx] = li;
        if (head != null) {
            li.setNext(head);
        }
    }

    T get(int key) {
        int indx = getHash(key);
        CHashItem<T> current = table[indx];
        do {
            if (current.getKey() == key)
                return current.getItem();
            current = current.getNext();
        } while (current != null);
        return null;
    }
}
