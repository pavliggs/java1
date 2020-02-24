package ru.progwards.pavliggs.N13dot1;

import java.util.*;

public class Test {
    public Set<Integer> a2set(int[] a) {
        Set<Integer> setArr = new HashSet<>();
        for (int i = 0; i < a.length; i++) {
            setArr.add(a[i]);
        }
        return setArr;
    }

    static int addAsStrings(int n1, int n2) {
        String concat = n1 + "" + n2;
        return Integer.parseInt(concat);
    }

    static long factorial(long n) {
        if (n == 0)
            return 1;
        else {
            long res = 1;
            for (int i = 1; i <= n; i++) {
                res *= i;
            }
            return res;
        }
    }

    static String textGrade(int grade) {
        if (grade == 0)
            return "не оценено";
        if (grade >= 1 && grade <= 20)
            return "очень плохо";
        if (grade >= 21 && grade <= 40)
            return "плохо";
        if (grade >= 41 && grade <= 60)
            return "удовлетворительно";
        if (grade >= 61 && grade <= 80)
            return "хорошо";
        if (grade >= 81 && grade <= 100)
            return "отлично";
        else
            return "не определено";
    }

    public static void main(String[] args) {
        System.out.println(new Test().a2set(new int[]{1, 5, 7 , 2 , 5}));
        System.out.println(addAsStrings(3, 7));
        System.out.println(factorial(5));
    }
}
