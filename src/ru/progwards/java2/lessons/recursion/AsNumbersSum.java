package ru.progwards.java2.lessons.recursion;

public class AsNumbersSum {
    public static String asNumbersSum(int number) {
        if (number == 1)
            return  "1";
        int res = number - 1;
        int res2 = number - res;
        return number + " = " + (res2) + " + " + asNumbersSum(res);
    }

    public static void main(String[] args) {
        System.out.println(asNumbersSum(5));
    }
}
