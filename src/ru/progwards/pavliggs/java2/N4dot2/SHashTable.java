package ru.progwards.pavliggs.java2.N4dot2;

public class SHashTable<T> {
    Object[] table;

    SHashTable(int n) {
        table = new Object[n];
    }

    public int getHash(int key) {
        return key % table.length;
    }

    void add(int key, T item) {
        int indx = getHash(key);
        table[indx] = item;
    }

    T get(int key) {
        int indx = getHash(key);
        return (T)table[indx];
    }

    public static void main(String[] args) {

    }
}
