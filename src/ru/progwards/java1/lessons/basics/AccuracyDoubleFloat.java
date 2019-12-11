package ru.progwards.java1.lessons.basics;

public class AccuracyDoubleFloat {
    public static final double PI = 3.14;

    public static double volumeBallDouble(double radius) {
        //чтобы результат деления 4/3 не был равен 1, приведем одно из чисел к вещественному типу данных
        return 4 / 3D * PI * Math.pow(radius, 3);
    }

    public static float volumeBallFloat(float radius) {
        /*по умолчанию метод Math.pow() возвращает значение типа double, поэтому необходимо явно
        привести результат метода Math.pow() к типу float*/
        return 4 / 3F * (float)PI * (float)Math.pow(radius, 3);
    }

    public static double calculateAccuracy(double radius) {
        return volumeBallDouble(radius) - volumeBallFloat((float)radius);
    }

    public static void main(String[] args) {
        System.out.println(volumeBallDouble(6371.2));
        System.out.println(volumeBallFloat(6371.2F));
        System.out.println(calculateAccuracy(6371.2));
    }
}
