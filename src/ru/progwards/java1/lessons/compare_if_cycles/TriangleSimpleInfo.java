package ru.progwards.java1.lessons.compare_if_cycles;

public class TriangleSimpleInfo {
    public static int maxSide(int a, int b, int c) {
        //при помощи логического И составляем условия для определения наибольшей длины
        if (a > b && a > c)
            return a;
        else if (b > a && b > c)
            return b;
        else
            return c;
    }

    public static int minSide(int a, int b, int c) {
        //при помощи логического И составляем условия для определения наименьшей длины
        if (a < b && a < c)
            return a;
        else if (b < a && b < c)
            return b;
        else
            return c;
    }

    public static boolean isEquilateralTriangle(int a, int b, int c) {
        //если сторона A равна стороне B и равна стороне C, то треугольник является равносторонним
        if (a == b && a == c)
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        System.out.println(maxSide(9, 18, 15));
        System.out.println(minSide(5, 4, 7));
        System.out.println(isEquilateralTriangle(9, 9, 9));
    }
}
