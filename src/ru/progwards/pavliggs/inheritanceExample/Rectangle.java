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

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) return true;
        if (anObject == null || getClass() != anObject.getClass()) return false;
        Rectangle rectangle = (Rectangle) anObject;
        return Double.compare(area(), rectangle.area()) == 0;
    }
}
