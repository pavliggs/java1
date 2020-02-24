package ru.progwards.java1.lessons.queues;

import java.util.*;

public class CollectionsSort {
    public static void mySort(Collection<Integer> data) {
        Integer[] arrInt = data.toArray(new Integer[data.size()]);
        for (int i = 0; i < arrInt.length; i++) {
            for (int j = i + 1; j < arrInt.length; j++) {
                if (arrInt[i] > arrInt[j]) {
                    //при помощи дополнительной переменной поменяем местами элементы массива
                    int temp = arrInt[i];
                    arrInt[i] = arrInt[j];
                    arrInt[j] = temp;
                }
            }
        }
        data.clear();
        data.addAll(List.of(arrInt));
    }

    public static void minSort(Collection<Integer> data) {
        ArrayDeque<Integer> newData = new ArrayDeque<>();
        while (!data.isEmpty()) {
            newData.add(Collections.min(data));
            data.remove(Collections.min(data));
        }
        data.addAll(newData);
    }

    static void collSort(Collection<Integer> data) {
        Collections.sort((List)data);
    }

    public static Collection<String> compareSort() {
        final int ELEM_COUNT = 100;
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < ELEM_COUNT; i++) {
            list.add(random.nextInt());
        }

//        class Method implements Comparable<Method>{
//            String name;
//            long speed;
//            public Method(String name, long speed) {
//                this.name = name;
//                this.speed = speed;
//            }
//
//            @Override
//            public int compareTo(Method o) {
//                return Long.compare(speed, o.speed);
//            }
//        }

        long start = System.currentTimeMillis();
        mySort(list);
        long finish1 = System.currentTimeMillis() - start;

        start = System.currentTimeMillis();
        minSort(list);
        long finish2 = System.currentTimeMillis() - start;

        start = System.currentTimeMillis();
        collSort(list);
        long finish3 = System.currentTimeMillis() - start;

//        Method method1 = new Method("mySort", finish1);
//        Method method2 = new Method("minSort", finish2);
//        Method method3 = new Method("collSort", finish3);

        List<String> listResult = new ArrayList<>(List.of("mySort", "minSort", "collSort"));

        Collections.sort(listResult);

//        Comparator<Method> comparator = new Comparator<Method>() {
//            @Override
//            public int compare(Method o1, Method o2) {
//                return Long.compare(o1.speed, o2.speed);
//            }
//        };
//        for (int i = 0; i < listResult.size(); i++) {
//            for (int j = i + 1; j < listResult.size(); j++) {
//                if (list.get(i) > arrInt[j]) {
//                    int temp = arrInt[i];
//                    arrInt[i] = arrInt[j];
//                    arrInt[j] = temp;
//                }
//            }

        return listResult;
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        Collections.addAll(arrayDeque, -5, 15, 0, -20, 100, 70);

        System.out.println(arrayDeque);
//        mySort(arrayDeque);
//        minSort(arrayDeque);
        System.out.println(arrayDeque);

        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 100, 1, -7, 17, 9, 43);
        System.out.println(list);
        collSort(list);
        System.out.println(list);

        System.out.println(compareSort());
    }
}
