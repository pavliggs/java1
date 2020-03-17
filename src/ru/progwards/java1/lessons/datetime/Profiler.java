package ru.progwards.java1.lessons.datetime;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

public class Profiler {
    static Map<String, ProfilerInfo> mapInfo = new HashMap<>();
//    static List<StatisticInfo> listInfo = new ArrayList<>();
    static List<ProfilerInfo> listInfo = new ArrayList<>();
    static Map<String, StatisticInfo> mapStatistic = new HashMap<>();

    static class ProfilerInfo {
        private String name;
        private LocalTime enterTime;
        private LocalTime exitTime;
        private long fullTime;
        private long selfTime;
        private int count;

//        ProfilerInfo(String name, LocalTime enterTime) {
//            this.name = name;
//            this.enterTime = enterTime;
//            count = 1;
//        }

        ProfilerInfo(String name) {
            this.name = name;
            count = 1;
        }

        ProfilerInfo(String name, LocalTime enterTime) {
            this.name = name;
            this.enterTime = enterTime;
        }

//        ProfilerInfo(String name, LocalTime enterTime, LocalTime exitTime, long fullTime, int count) {
//            this.name = name;
//            this.enterTime = enterTime;
//            this.exitTime = exitTime;
//            this.fullTime = fullTime;
//            this.count = count;
//        }

        String getName() {
            return name;
        }

        LocalTime getEnterTime() {
            return enterTime;
        }

        LocalTime getExitTime() {
            return exitTime;
        }

        long getFullTime() {
            return fullTime;
        }

        int getCount() {
            return count;
        }

        @Override
        public String toString() {
            return "ProfilerInfo{" +
                    "name='" + name + '\'' +
                    ", enterTime=" + enterTime +
                    ", exitTime=" + exitTime +
                    ", fullTime=" + fullTime +
                    ", count=" + count +
                    '}';
        }
    }

    public static void enterSection(String name) {
        // если mapInfo содержит значение (объект) с таким ключом, то прибавляем увеличиваем у объекта count на 1
//        if (mapInfo.containsKey(name)) {
//            ++mapInfo.get(name).count;
//            mapInfo.get(name).enterTime = LocalTime.now();
//        }
//        mapInfo.putIfAbsent(name, new ProfilerInfo(name, LocalTime.now()));
//        listInfo.add(new ProfilerInfo(name, mapInfo.get(name).getEnterTime()));

        if (mapInfo.containsKey(name)) {
            ++mapInfo.get(name).count;
        }
        mapInfo.putIfAbsent(name, new ProfilerInfo(name));
        listInfo.add(new ProfilerInfo(name, LocalTime.now()));
    }

    public static void exitSection(String name) {
//        mapInfo.get(name).exitTime = LocalTime.now();
//
//        listInfo.get(listInfo.size()-1).exitTime = mapInfo.get(name).getExitTime();
//
//        Duration duration = Duration.between(mapInfo.get(name).getEnterTime(), mapInfo.get(name).getExitTime());
//        mapInfo.get(name).fullTime = duration.toMillis();
//
//        listInfo.get(listInfo.size()-1).fullTime = mapInfo.get(name).getFullTime();
//
//        listInfo.remove(listInfo.size()-1);

//        if (mapStatistic.containsKey(name)) {
//            long newDur = mapStatistic.get(name).getFullTime() + duration.toMillis();
//            mapStatistic.put(name, new StatisticInfo(name, newDur, mapInfo.get(name).getCount()));
//        }
//        mapStatistic.putIfAbsent(name, new StatisticInfo(name, mapInfo.get(name).getFullTime(), mapInfo.get(name).getFullTime(), mapInfo.get(name).getCount()));

        Duration duration = Duration.between(listInfo.get(listInfo.size()-1).getEnterTime(), LocalTime.now());
        long fullTime = duration.toMillis();

        listInfo.remove(listInfo.size()-1);

        mapStatistic.putIfAbsent(name, new StatisticInfo(name, fullTime, fullTime, mapInfo.get(name).getCount()));
    }

//    public static boolean isThereNesting(ProfilerInfo obj, List<ProfilerInfo> list) {
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i).getEnterTime().isAfter(obj.getEnterTime()) && list.get(i).getExitTime().isBefore(obj.getExitTime()))
//                return true;
//        }
//        return false;
//    }

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
//        enterSection("1");
//        for (int i = 0; i < 200; i++) {
//            for (int j = i+1; j < 2000; j++) {
//                System.out.println(fiboNumber(j));
//            }
//        }
//        enterSection("2");
//        for (int i = 0; i < 200; i++) {
//            for (int j = i+1; j < 2000; j++) {
//                System.out.println(fiboNumber(j));
//            }
//        }
//        enterSection("3");
//        for (int i = 0; i < 200; i++) {
//            for (int j = i+1; j < 2000; j++) {
//                System.out.println(fiboNumber(j));
//            }
//        }
//        exitSection("3");
//        exitSection("2");
//        for (int i = 0; i < 200; i++) {
//            for (int j = i+1; j < 2000; j++) {
//                System.out.println(fiboNumber(j));
//            }
//        }
//        exitSection("1");
//        enterSection("2");
//        for (int i = 0; i < 200; i++) {
//            for (int j = i+1; j < 2000; j++) {
//                System.out.println(fiboNumber(j));
//            }
//        }
//        exitSection("2");
//        System.out.println(mapInfo);
//        System.out.println(listInfo);
//        System.out.println(mapStatistic);
    }

}
