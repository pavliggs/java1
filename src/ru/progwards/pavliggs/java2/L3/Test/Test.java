package ru.progwards.pavliggs.java2.L3.Test;

import org.apache.logging.log4j.core.appender.rolling.action.IfNot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test {
    public enum CompareResult {
        LESS,
        EQUAL,
        GREATER
    }

    public static<T extends Comparable<T>> CompareResult compare(T o1, T o2) {
        if (o1.compareTo(o2) > 0)
            return CompareResult.GREATER;
        if (o1.compareTo(o2) < 0)
            return CompareResult.LESS;
        return CompareResult.EQUAL;
    }

    static<T> void swap(List<T> list, int indx1, int indx2) {
        T tmp = list.get(indx1);
        list.set(indx1, list.get(indx2));
        list.set(indx2, tmp);
    }

    static<T> List<T> from(T[] arr) {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        return list;
    }

    public static void main(String[] args) {
        compare(1, 5);
        System.out.println(compare("ВАся", "Петя"));
    }
}
