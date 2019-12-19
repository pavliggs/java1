package ru.progwards.pavliggs.inheritanceExample;

public class Triangle extends Segment{
    double b, c;

    Triangle(double a, double b, double c) {
        super(a);
        this.b = b;
        this.c = c;
    }

    @Override
    double perimeter() {
        return a + b + c;
    }

    @Override
    double area() {
        //вычисляем площадь по формуле Герона
        double hP = perimeter() / 2;
        return Math.sqrt(hP * (hP - a) * (hP - b) * (hP - c));
    }

    @Override
    public String toString() {
        return "Треугольник со сторонами " + a + ", " + b + " и " + c;
    }
}
