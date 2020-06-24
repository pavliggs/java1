package ru.progwards.java2.lessons.trees;

import ru.progwards.pavliggs.java2.N8dot1.BinaryTree;

import java.util.TreeMap;

public class TestTree {
    static final int ITERATIONS = 1000_000;

    public static void main(String[] args) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        AvlTree<Integer, Integer> avlTree = new AvlTree<>();

        long start1 = System.currentTimeMillis();
        for (int i = 0; i < ITERATIONS; i++) {
            treeMap.put(i, i);
        }
        long finish = System.currentTimeMillis() - start1;
        System.out.println("Добавление в TreeMap длится - " + finish);

        long start2 = System.currentTimeMillis();
        for (int i = 0; i < ITERATIONS; i++) {
            avlTree.put(i, i);
        }
        finish = System.currentTimeMillis() - start2;
        System.out.println("Добавление в AvlTree длится - " + finish);

//        long start4 = System.currentTimeMillis();
//        for (int i = 0; i < ITERATIONS; i++) {
//            treeMap.remove(i);
//        }
//        finish = System.currentTimeMillis() - start4;
//        System.out.println("Удаление из TreeMap длится - " + finish);
//
//        long start5 = System.currentTimeMillis();
//        for (int i = 0; i < ITERATIONS; i++) {
//            avlTree.delete(i);
//        }
//        finish = System.currentTimeMillis() - start5;
//        System.out.println("Удаление из AvlTree длится - " + finish);

        long start6 = System.currentTimeMillis();
        for (int i = 0; i < ITERATIONS; i++) {
            treeMap.get(i);
        }
        finish = System.currentTimeMillis() - start6;
        System.out.println("Поиск в TreeMap длится - " + finish);

        long start7 = System.currentTimeMillis();
        for (int i = 0; i < ITERATIONS; i++) {
            avlTree.find(i);
        }
        finish = System.currentTimeMillis() - start7;
        System.out.println("Поиск в AvlTree длится - " + finish);

//        for (int i = 0; i < ITERATIONS; i++) {
//            avlTree.delete(i);
//        }

//        avlTree.delete(2);
//        avlTree.delete(4);
//        avlTree.delete(3);

//        System.out.println(avlTree.find(123765));


//        System.out.println(avlTree.root.find(0).height);
//        System.out.println(avlTree.root.find(1).height);
//        System.out.println(avlTree.root.find(2).height);
//        System.out.println(avlTree.root.find(3).height);
//        System.out.println(avlTree.root.find(4).height);
//        System.out.println(avlTree.root.find(5).height);
//        System.out.println(avlTree.root.find(6).height);

//        avlTree.process(System.out::println);
    }
}
