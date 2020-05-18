package ru.progwards.pavliggs.java2.L3;

// пример обобщенного метода
public class MyMath {

    public static <T> T getMiddle(T... args) {
        return args[args.length/2];
    }

    public static void main(String[] args) {
        System.out.println(MyMath.<Integer>getMiddle(4, 1, 3, 54, 8, 11));
    }
}
