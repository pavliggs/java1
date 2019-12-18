package ru.progwards.pavliggs.inheritanceExample;

public class Circle extends Figure{
    double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double perimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    double area() {
        return Math.PI * radius * radius;
    }
}
