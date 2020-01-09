package ru.progwards.pavliggs.abstractClasses;

public abstract class Figure {
    public int compareArea(Figure figure) {
        return Double.compare(this.area(), figure.area());
    }

    //периметр фигуры
    abstract double perimeter();

    //площадь фигуры
    double area() {
        return 0d;
    }
}
