package ru.progwards.pavliggs.N15dot3;

import java.util.TreeMap;

public class Test {
    void checkAndAdd(TreeMap<Integer, String> map, Integer key, String value) {
        if (!map.isEmpty() && key > map.firstKey() && key < map.lastKey()) {
            if (map.putIfAbsent(key, value) == null)
                map.put(key, value);
        }
    }

    public static void main(String[] args) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(0, "zero");
        treeMap.put(5, "five");
        treeMap.put(9, "nine");

        System.out.println(treeMap);
//        System.out.println(treeMap.lastKey());

        new Test().checkAndAdd(treeMap, 0, "zeroo");
        new Test().checkAndAdd(treeMap, 2, "two");
        new Test().checkAndAdd(treeMap, 7, "seven");
        new Test().checkAndAdd(treeMap, 10, "ten");
        new Test().checkAndAdd(treeMap, 2, "twooo");

        System.out.println(treeMap);
    }
}
