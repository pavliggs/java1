package ru.progwards.java1.lessons.compare_if_cycles;

public class TriangleInfo {
    public static boolean isTriangle(int a, int b, int c) {
        //согласно правилу построения треугольника составим соответствующее условие
        if (a < (b + c) && b < (a + c) && c < (a + b))
            return true;
        else
            return false;
    }

    public static boolean isRightTriangle(int a, int b, int c) {
        /*
        при помощи функции isTriangle добавим дополнительное условие, которое будет возвращать false
        и соответствующее сообщение, если из таких сторон нельзя построить треугольник
        */
        if (isTriangle(a, b, c)) {
            //используя теорему Пифагора составим условие для определения прямоугольного треугольника
            if (a * a == b * b + c * c || b * b == a * a + c * c || c * c == a * a + b * b)
                return true;
            else
                return false;
        } else {
            System.out.println("Не удается построить треугольник по данным сторонам");
            return false;
        }
    }

    public static boolean isIsoscelesTriangle(int a, int b, int c) {
        if (isTriangle(a, b, c)) {
            //условие, которое будет определять равнобедренный треугольник или нет
            if (a == b || a == c || c == b)
                return true;
            else
                return false;
        } else {
            System.out.println("Не удается построить треугольник по данным сторонам");
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(isTriangle(14, 9, 6));
        System.out.println(isRightTriangle(3, 4, 5));
        System.out.println(isIsoscelesTriangle(6, 4, 4));
    }
}
