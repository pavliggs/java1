package ru.progwards.pavliggs.java2.N2dot3;

import java.time.Instant;

public class Test {
    public static int sumSequence(int n) {
        if (n == 1)
            return n;
        return sumSequence(n - 2) + n;
    }

    String reverseChars(String str) {
        char[] arr = str.toCharArray();
        if (arr.length == 1)
            return str;
        if (arr.length == 0)
            return "";
        // присвоим str2 значение str без первого символа
        String str2 = str.substring(1, arr.length);
        return reverseChars(str2) + str.substring(0, 1);
    }

    /*
     * reverseChars(12345) -> 2345 + 1
     * reverseChars(2345)  -> 345 + 2
     * reverseChars(345)   -> 45 + 3
     * reverseChars(45)    -> 5 + 4
     * reverseChars(5)     -> 5
     * 5
     * 5 + 4 -> 54
     * 54 + 3 -> 543
     * 543 + 2 -> 5432
     * 5432 + 1 -> 54321
     * */

    public static void main(String[] args) {
        System.out.println(sumSequence(5));
        System.out.println(new Test().reverseChars("asdfg"));
        System.out.println(new Test().reverseChars(""));
    }
}
