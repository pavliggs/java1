package ru.progwards.pavliggs.N13dot1;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {
    public Set<Integer> a2set(int[] a) {
        Set<Integer> setArr = new HashSet<>();
        for (int i = 0; i < a.length; i++) {
            setArr.add(a[i]);
        }
        return setArr;
    }

    public static void main(String[] args) {
        System.out.println(new Test().a2set(new int[]{1, 5, 7 , 2 , 5}));
    }
}
