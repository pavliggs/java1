package ru.progwards.pavliggs.inheritanceExample;

public class Rectangle extends Square {
    double b;

    Rectangle(double a, double b) {
        super(a);
        this.b = b;
    }

    @Override
    double perimeter() {
        return 2 * (a + b);
    }

    @Override
    double area() {
        return a * b;
    }

    @Override
    public String toString() {
        return "Прямоугольник со сторонами " + a + " и " + b;
    }
}
