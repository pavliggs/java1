package ru.progwards.java1.lessons.datetime;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

public class Profiler {
    static List<Long> list = new ArrayList<>();
    static Queue<ProfilerInfo> stack = new ArrayDeque<>();

    static class ProfilerInfo {
        private String name;
        private LocalTime enterTime;
        private long fullTime;

        ProfilerInfo(String name, LocalTime enterTime) {
            this.name = name;
            this.enterTime = enterTime;
        }

        String getName() {
            return name;
        }

        LocalTime getEnterTime() {
            return enterTime;
        }
    }

    public static void enterSection(String name) {

    }

    public static void exitSection(String name) {

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
