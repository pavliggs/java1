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
        int count = 1;
        int maxCount = 1;
        /* присвоим переменной ссылку на первый элемент коллекции, чтобы он выводился в случае,
         если в коллекции всего 1 элемент */
        String repeatedMax = ((List<String>)names).get(0);
        for (int i = 1; i < names.size(); i++) {
            // если элементы равны, то увеличиваем count на 1
            if (((List<String>)names).get(i) == ((List<String>)names).get(i - 1)) {
                count++;
                /* если count больше maxCount, то присваиваем maxCount значение count
                * и записываем в repeatedMax ссылку на текущий элемент*/
                if (count > maxCount) {
                    repeatedMax = ((List<String>)names).get(i);
                    maxCount = count;
                }
            }
            // если повторяющиеся подряд элементы закончились, то присваиваем count начальное значение (1)
            if (((List<String>)names).get(i) != ((List<String>)names).get(i - 1)) {
                count = 1;
            }
        }
        return repeatedMax + ":" + maxCount;
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
        list3.add("Татьяна");
        list3.add("Ольга");
        list3.add("Ирина");
        list3.add("Мария");
        list3.add("Мария");
        list3.add("Мария");
        list3.add("Борис");
        list3.add("Феликс");
        list3.add("Феликс");
        list3.add("Феликс");
        list3.add("Феликс");
        list3.add("Феликс");
        list3.add("Феликс");

        System.out.println(findSimilar(list3));
    }
}
