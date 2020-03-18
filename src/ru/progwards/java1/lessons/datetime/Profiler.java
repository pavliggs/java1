package ru.progwards.java1.lessons.datetime;

import java.time.Instant;
import java.util.*;

public class Profiler {
    private static Deque<Long> sectionStartTimeDeque = new ArrayDeque<>();
    private static Deque<String> sectionNameDeque = new ArrayDeque<>();
    private static LinkedHashMap<String, StatisticInfo> sectionMap = new LinkedHashMap<>();

    public static void enterSection(String name) {
        // добаялем в sectionMap объект StatisticInfo (секцию) с именем name
        sectionMap.putIfAbsent(name, new StatisticInfo(name));
        // добаялем в sectionStartTimeDeque время входа этой секции в миллисекундах
        sectionStartTimeDeque.push(Instant.now().toEpochMilli());
        // добавляем имя name этой секции в sectionNameDeque
        sectionNameDeque.push(name);
    }

    public static void exitSection(String name) {
        // на выходе из секции вычисляем её продолжительность и удаляем время входа секции из стека
        int period = (int)(Instant.now().toEpochMilli() - sectionStartTimeDeque.pop());
        // увеличиваем счетчик на 1
        sectionMap.get(name).count++;
        // увеличиваем fullTime и selfTime на period
        sectionMap.get(name).fullTime += period;
        sectionMap.get(name).selfTime += period;
        // удаляем имя этой секции из стека
        sectionNameDeque.pop();
        // еслли стек с именами секций не пустой, то это занчит, что секция является вложенной в другую
        if (!sectionNameDeque.isEmpty()) {
            // присваиваем externalSectionName имя секции, в которую вложена данная секция (секция из которой мы выходим)
            String externalSectionName = sectionNameDeque.peek();
            // уменьшаем selfTime наружной секции на period (при выходе из этой секции её selfTime будет отрицательным)
            sectionMap.get(externalSectionName).selfTime -= period;
        }
    }

    public static List<StatisticInfo> getStatisticInfo() {
        Comparator<StatisticInfo> comparator = new Comparator<StatisticInfo>() {
            @Override
            public int compare(StatisticInfo o1, StatisticInfo o2) {
                return o1.sectionName.compareTo(o2.sectionName);
            }
        };

        ArrayList<StatisticInfo> list = new ArrayList<>(sectionMap.values());
        // сортируем объекты по sectionName
        list.sort(comparator);

        return list;
    }

    public static int fiboNumber(int n) {
        int fibo1 = 1;
        int fibo2 = 1;
        for (int i = 2; i < n; i++) {
            int fiboNext = fibo1 + fibo2;
            fibo1 = fibo2;
            fibo2 = fiboNext;
        }
        return fibo2;
    }

    public static void main(String[] args) {
        enterSection("3");
        for (int i = 0; i < 200; i++) {
            for (int j = i+1; j < 2000; j++) {
                fiboNumber(j);
            }
        }
        enterSection("-5");
        for (int i = 0; i < 200; i++) {
            for (int j = i+1; j < 2000; j++) {
                fiboNumber(j);
            }
        }
        enterSection("1");
        for (int i = 0; i < 200; i++) {
            for (int j = i+1; j < 2000; j++) {
                fiboNumber(j);
            }
        }
        exitSection("1");
        exitSection("-5");
        for (int i = 0; i < 200; i++) {
            for (int j = i+1; j < 2000; j++) {
                fiboNumber(j);
            }
        }
        exitSection("3");
        System.out.println(getStatisticInfo());
    }

}
