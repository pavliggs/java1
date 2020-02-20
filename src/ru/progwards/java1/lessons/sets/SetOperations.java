package ru.progwards.java1.lessons.sets;

import java.util.HashSet;
import java.util.Set;

public class SetOperations {
    public static Set<Integer> union(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> result = new HashSet<>(set1);
        result.addAll(set2);
        return result;
    }

    public static Set<Integer> intersection(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> result = new HashSet<>(set1);
        result.retainAll(set2);
        return result;
    }

    public static Set<Integer> difference(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> result = new HashSet<>(set1);
        result.removeAll(set2);
        return result;
    }

    public static Set<Integer> symDifference(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> result1 = new HashSet<>(set1);
        Set<Integer> result2 = new HashSet<>(set2);
        result1.removeAll(set2);
        result2.removeAll(set1);
        result1.addAll(result2);
        return result1;
    }

    public static void main(String[] args) {
        Set<Integer> set1 = Set.of(1, 3, 5, 15, 2, 7);
        Set<Integer> set2 = Set.of(21, 100, 5, 15, 30, 9);

        System.out.println(set1);
        System.out.println(set2);

        Set<Integer> result1 = union(set1, set2);
        Set<Integer> result2 = intersection(set1, set2);
        Set<Integer> result3 = difference(set1, set2);
        Set<Integer> result4 = symDifference(set1, set2);

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
    }
}
