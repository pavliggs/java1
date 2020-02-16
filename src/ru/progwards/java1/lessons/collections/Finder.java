package ru.progwards.java1.lessons.collections;

import java.util.*;

public class Finder {
    public static Collection<Integer> findMinSumPair(Collection<Integer> numbers) {
        List<Integer> listIndex = new ArrayList<>();
        // заводим начальные значения индексов чисел с минимальной суммой
        int indexMin1 = 0;
        int indexMin2 = 1;
        for (int i = 0; i < numbers.size() - 1; i++) {
            int sumCurrent = ((List<Integer>)numbers).get(i) + ((List<Integer>)numbers).get(i+1);
            int sumMin = ((List<Integer>)numbers).get(indexMin1) + ((List<Integer>)numbers).get(indexMin2);
            if (sumCurrent < sumMin) {
                indexMin1 = i;
                indexMin2 = i + 1;
            }
        }
        listIndex.add(indexMin1);
        listIndex.add(indexMin2);
        return listIndex;
    }

    public static Collection<Integer> findLocalMax(Collection<Integer> numbers) {
        List<Integer> listValues = new ArrayList<>();
        for (int i = 1; i < numbers.size() - 1; i++) {
            int valuePrev = ((List<Integer>)numbers).get(i - 1);
            int valueCurrent = ((List<Integer>)numbers).get(i);
            int valueNext = ((List<Integer>)numbers).get(i + 1);
            if (valuePrev < valueCurrent && valueNext < valueCurrent)
                listValues.add(valueCurrent);
        }
        return listValues;
    }

    public static boolean findSequence(Collection<Integer> numbers) {
        List<Integer> listNumbers = new ArrayList<>();
        for (int i = 1; i <= numbers.size(); i++) {
            listNumbers.add(i);
        }
        // проверяем содержит ли коллекция numbers все элементы коллекции listNumbers
        if (numbers.containsAll(listNumbers))
            return true;
        return false;
    }

    public static String findSimilar(Collection<String> names) {
        List<String> listRepeat = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            if (i + 1 < names.size()) {
                if (((List<String>)names).get(i) == ((List<String>)names).get(i + 1)) {
                    if (!listRepeat.contains(((List<String>)names).get(i))) {
                        listRepeat.add(((List<String>)names).get(i));
                        listRepeat.add(((List<String>)names).get(i+1));
                    } else
                        listRepeat.add(((List<String>)names).get(i));
                }
            }
        }
        System.out.println(listRepeat);
        return " ";
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(3);
        list.add(0);
        list.add(1);
        list.add(9);
        list.add(2);
        list.add(4);
        list.add(5);

        System.out.println(list);
//        System.out.println(findMinSumPair(list));
        System.out.println(findLocalMax(list));

        List<Integer> list2 = new ArrayList<>();
        list2.add(8);
        list2.add(3);
        list2.add(6);
        list2.add(1);
        list2.add(7);
        list2.add(2);
        list2.add(4);
        list2.add(5);
        System.out.println(findSequence(list2));

        List<String> list3 = new ArrayList<>();
        list3.add("Кирилл");
        list3.add("Кирилл");
        list3.add("Кирилл");
        list3.add("Кирилл");
        list3.add("Кирилл");
        list3.add("3");
        list3.add("ds");
        list3.add("5");
        list3.add("Мария");
        list3.add("Мария");
        list3.add("Мария");
        System.out.println(findSimilar(list3));
    }
}
