package ru.progwards.java2.lessons.basetypes;

import java.util.Iterator;

public class MainDoubleHashTable {
    public static void main(String[] args) {
        DoubleHashTable<IntHashKey, Integer> doubleHashTable = new DoubleHashTable<>();

        for (int i = 377; i < 500; i++) {
            doubleHashTable.add(new IntHashKey(i), i);
        }

        System.out.println(doubleHashTable.get(new IntHashKey(400)));

        doubleHashTable.change(new IntHashKey(400), new IntHashKey(450));

        System.out.println(doubleHashTable.get(new IntHashKey(400)));

        System.out.println(doubleHashTable.get(new IntHashKey(450)));

//        doubleHashTable.add(new IntHashKey(1222), 1222);
//
//        doubleHashTable.change(new IntHashKey(377), new IntHashKey(1222));
//
//        System.out.println(doubleHashTable.size());
//
//        System.out.println(doubleHashTable.get(new IntHashKey(794)));
//
//        System.out.println(doubleHashTable.get(new IntHashKey(1222)));
//        System.out.println(doubleHashTable.get(new IntHashKey(377)));
//
//        Iterator<DoubleHashTable.Node<IntHashKey, Integer>> iterator = doubleHashTable.getIterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
//
//
//        DoubleHashTable<StringHashKey, Integer> hashTable = new DoubleHashTable<>();
//
//        hashTable.add(new StringHashKey("Hi"), 10);
//        hashTable.add(new StringHashKey("World"), 21);
//        hashTable.add(new StringHashKey("Maps"), 567);
//        hashTable.add(new StringHashKey("Cheese"), 1);
//        hashTable.add(new StringHashKey("Milk"), 23);
//        hashTable.add(new StringHashKey("Hello, world!"), 98);
//        hashTable.add(new StringHashKey("Ann"), 67);
//        hashTable.add(new StringHashKey("Lucky"), 111);
//        hashTable.add(new StringHashKey("New York"), 2236);
//        hashTable.add(new StringHashKey("Fantasy"), 568);
//        hashTable.add(new StringHashKey("Christmas"), 23365);
//        hashTable.add(new StringHashKey("Happy"), 235);
//        hashTable.add(new StringHashKey("Bold"), 83);
//
//        Iterator<DoubleHashTable.Node<StringHashKey, Integer>> iterator1 = hashTable.getIterator();
//
//        while (iterator1.hasNext())
//            System.out.println(iterator1.next());
//
//        System.out.println(hashTable.get(new StringHashKey("Milk")));
    }
}
