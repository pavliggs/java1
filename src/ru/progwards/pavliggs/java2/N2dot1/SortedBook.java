package ru.progwards.pavliggs.java2.N2dot1;

import java.util.*;

public class SortedBook {
    public static void main(String[] args) {
        List<Book> list = new ArrayList<>(List.of(
                new Book("Капитанская дочка", "Пушкин", 545),
                new Book("Кавказский пленник", "Лермонтов", 691),
                new Book("Мертвые души", "Гоголь", 800)
        ));

        System.out.println("По автору");
        list.sort((o1, o2) -> o1.author.compareTo(o2.author));
        list.forEach(element -> System.out.println(element));
        // или можно так (лучше делать так)
        list.forEach(System.out::println);
        // или можно так
//        Consumer<Book> consumer = element -> System.out.println(element);
//        list.forEach(consumer);
        System.out.println("\nПо названию");
        list.sort(Comparator.comparing(a -> a.name));
        list.forEach(element -> System.out.println(element));

        Set<Integer> set = new TreeSet<>(Comparator.comparing(o -> Math.abs(o)));
        set.addAll(Set.of(-1, 9, -8, 7, 5, -10));
        System.out.println(set);
    }
}
