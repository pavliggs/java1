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

    static class Method implements Comparable<Method> {
        String name;
        long speed;
        public Method(String name, long speed) {
            this.name = name;
            this.speed = speed;
        }

        // сравнение сделал по наименованию метода
        @Override
        public int compareTo(Method o) {
            return name.compareTo(o.name);
        }

        @Override
        public String toString() {
            return "Method{" +
                    "name='" + name + '\'' +
                    ", speed=" + speed +
                    '}';
        }
    }

    // метод по добавлению в список имён метод в нужном порядке
    public static Collection<String> getListNamesMethods(Collection<Method> data) {
        List<String> listNameMethod = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            listNameMethod.add(((ArrayList<Method>)data).get(i).name);
        }
        return listNameMethod;
    }

    public static Collection<String> compareSort() {
        /* создаём список и заполняем его произвольными значениями */
        final int ELEM_COUNT = 10_000;
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        for (int i = ELEM_COUNT; i >= 0; i--) {
            list.add(random.nextInt());
        }

        long start = System.currentTimeMillis();
        mySort(list);
        long finish1 = System.currentTimeMillis() - start;

        start = System.currentTimeMillis();
        minSort(list);
        long finish2 = System.currentTimeMillis() - start;

        start = System.currentTimeMillis();
        collSort(list);
        long finish3 = System.currentTimeMillis() - start;

        // создаём объекты с наименованием и временем работы метода
        Method method1 = new Method("mySort", finish1);
        Method method2 = new Method("minSort", finish2);
        Method method3 = new Method("collSort", finish3);

        // помещаем эти объекты в список
        List<Method> listMethod = new ArrayList<>(List.of(method1, method2, method3));
        // сортируем список согласно компаратору, то есть по имени метода (по алфавиту)
        Collections.sort(listMethod);

        /* при помощи цикла меняем местами свойства объектов (сортировка по времени работы метода, а если время
         * равно, то по алфавиту) */
        for (int i = 0; i < listMethod.size(); i++) {
            for (int j = i+1; j < listMethod.size(); j++) {
                if (listMethod.get(i).speed > listMethod.get(j).speed) {
                    long speed = listMethod.get(i).speed;
                    String name = listMethod.get(i).name;
                    listMethod.get(i).speed = listMethod.get(j).speed;
                    listMethod.get(i).name = listMethod.get(j).name;
                    listMethod.get(j).speed = speed;
                    listMethod.get(j).name = name;
                }
            }
        }
        return getListNamesMethods(listMethod);
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        Collections.addAll(arrayDeque, -5, 15, 0, -20, 100, 70);

        System.out.println(arrayDeque);
        mySort(arrayDeque);
        minSort(arrayDeque);
        System.out.println(arrayDeque);

        List<Integer> list = new ArrayList<>();
        for (int i = 10; i > 0 ; i--) {
            list.add(i);
        }
        System.out.println(list);
        collSort(list);
        System.out.println(list);

        System.out.println(compareSort());
    }
}
