package ru.progwards.java2.lessons.recursion;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GoodsWithLambda {
    List<Goods> list;

    public GoodsWithLambda() {
        list = new ArrayList<>();
    }

    void setGoods(List<Goods> list) {
        Instant date = Instant.now();
        Collections.addAll(list,
                new Goods("Bread", "AERX-AGH-988", 200, 30, date.plus(Duration.ofDays(3))),
                new Goods("Milk", "dsAZ-AGH-500", 187, 50, date.plus(Duration.ofDays(10))),
                new Goods("Cheese", "lMyT-CCV-101", 85, 300, date.plus(Duration.ofDays(30))),
                new Goods("Tea", "mmAZ-SSD-218", 250, 100, date.plus(Duration.ofDays(365))),
                new Goods("Cake", "aerT-KGF-500", 85, 60, date.plus(Duration.ofDays(30))),
                new Goods("Groats", "dpZA-CCV-097", 199, 70, date.plus(Duration.ofDays(97)))
        );
    }

    List<Goods> sortByName() {
        List<Goods> sortedList = list.stream().sorted(Comparator.comparing(o -> o.name)).collect(Collectors.toList());
        return sortedList;
    }

    List<Goods> sortByNumber() {
        List<Goods> sortedList =
                list.stream().sorted(Comparator.comparing(o -> o.number.toLowerCase())).collect(Collectors.toList());
        return sortedList;
    }

    List<Goods> sortByPartNumber() {
        List<Goods> sortedList =
                list.stream().sorted(Comparator.comparing(o -> o.number.toLowerCase().substring(0, 3))).
                        collect(Collectors.toList());
        return sortedList;
    }

    List<Goods> sortByAvailabilityAndNumber() {
        List<Goods> sortedList =
                list.stream().sorted(Comparator.comparing(o -> o.number.toLowerCase())).
                        sorted(Comparator.comparing(o -> o.available)).collect(Collectors.toList());
        return sortedList;
    }

    List<Goods> expiredAfter(Instant date) {
        List<Goods> sortedList =
                list.stream().sorted(Comparator.comparing(o -> o.expired)).takeWhile(o -> o.expired.isBefore(date)).
                        collect(Collectors.toList());
        return sortedList;
    }

    List<Goods> сountLess(int count) {
        List<Goods> sortedList =
                list.stream().sorted(Comparator.comparing(o -> o.available)).takeWhile(o -> o.available < count).
                        collect(Collectors.toList());
        return sortedList;
    }

    List<Goods> сountBetween(int count1, int count2) {
        Predicate<Goods> greaterCount1 = o -> o.available > count1;
        Predicate<Goods> belowCount2 = o -> o.available < count2;
        Predicate<Goods> compose = greaterCount1.and(belowCount2);
        List<Goods> sortedList =
                list.stream().filter(compose).collect(Collectors.toList());
        return sortedList;
    }

    public static void main(String[] args) {
        GoodsWithLambda obj = new GoodsWithLambda();
        obj.setGoods(obj.list);
        System.out.println(obj.sortByName());
        System.out.println(obj.sortByNumber());
        System.out.println(obj.sortByPartNumber());
        System.out.println(obj.sortByAvailabilityAndNumber());
        Instant date = Instant.now().plus(Duration.ofDays(25));
        System.out.println(obj.expiredAfter(date));
        System.out.println(obj.сountLess(100));
        System.out.println(obj.сountBetween(100, 200));
    }
}
