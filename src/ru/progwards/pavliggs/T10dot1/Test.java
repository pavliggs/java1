package ru.progwards.pavliggs.T10dot1;

public class Test {

    public static Integer sqr(Integer n) {

        try {
            return n * n;
        } catch (NullPointerException e) {
            return -1;
        }
    }

    public static void main(String[] args) {
        System.out.println(sqr(5));
        System.out.println(sqr(null));
    }
}
