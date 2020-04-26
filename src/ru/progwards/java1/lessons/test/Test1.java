package ru.progwards.java1.lessons.test;

import com.google.common.collect.TreeMultimap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Test1 {
    public static void main(String[] args) {
//        TreeMultimap<String, String> map = TreeMultimap.create();
//        map.put("картошка","соус");
//        map.put("картошка","соус");
//        map.put("картошка","кетчуп");
//        map.put("картошка","кетчуп");
//        System.out.println(map);
//        Set<Map.Entry<String, String>> elemants = map.entries();
//        System.out.println(elemants);
//        TreeMultimap<String, String> map1 = TreeMultimap.create(map);
//        System.out.println(map1);
//
//
//        String str = "картошка/соуc";
//        String[] arr = str.split("[/]");
//        System.out.println(arr.length);
//        System.out.println(arr[1]);

        System.out.println(Integer.valueOf(5));
        String str = null;
        System.out.println(str);
        Integer integer = null;
        System.out.println(integer);

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(5);
        }
        System.out.println(list);
        list.set(1, 7);
        System.out.println(list);
    }
}
