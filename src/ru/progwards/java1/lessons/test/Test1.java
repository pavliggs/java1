package ru.progwards.java1.lessons.test;

import com.google.common.collect.TreeMultimap;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Test1 {
    public static void main(String[] args) {
//        try(FileReader fileReader = new FileReader("letter.txt")) {
//            Scanner scanner = new Scanner(fileReader);
//            while (scanner.hasNextLine()) {
//                String str = scanner.next();
//                System.out.println(str);
//            }
//        } catch (IOException e) {
//            System.out.println(e);
//        }

//        System.out.format("|%04d|%#x|%2.1f|", 2, 15, 3.25);


//        TreeMultimap<Integer, String> map = TreeMultimap.create();
//        map.put(1, "кот");
//        map.put(1, "собака");
//        map.put(1, "белка");
//        map.put(2, "олень");
//        System.out.println(map);
//        System.out.println(map.size());
//        Object[] arr = map.values().toArray();
//        System.out.println(Arrays.toString(arr));
//        Object[] arr2 = map.keySet().toArray();
//        System.out.println(Arrays.toString(arr2));

//        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();
//        map.put(1, "один");
//        map.put(1, "второй раз один");
//        map.put(2, "два");
//        map.put(3, "три");
//        map.put(4, "четыре");
//        map.put(5, "пять");
//        System.out.println(map);
//
//
//        System.out.println(17 % 10);
//        System.out.println(12 % 10);
//        System.out.println(7 % 10);

        Map<Integer, Object> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        set.add("str");
        map.put(1, set);
        if (map.get(1) == null) {
            Set<String> set1 = new HashSet<>();
            set1.add("hello");
            map.put(1, set);
        } else {
            Set<String> set1 = (Set<String>) map.get(1);
            set1.add("buy buy");
        }

        System.out.println(map);


//        Set<Integer> set = new HashSet<>();
//        System.out.println(set == null);
//
////        map.put(1, set);
////        map.put(2, "two");
////        map.put(3, new Object());
////        System.out.println(map);
//
//        TreeMultimap<Integer, String> k = TreeMultimap.create();
//        k.put(1, "dsd");
//        k.put(1, "dsds");
//        k.put(1, "dsdcsacs");
//        k.put(1, "dsdxsfcv");
//        Collection<String> s = k.values();
//        System.out.println(s);

    }
}
