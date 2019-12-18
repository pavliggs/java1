package ru.progwards.pavliggs.inheritanceExample;

public class Square extends Segment {
    Square(double a) {
        super(a);
        this.a = a;
    }

    @Override
    double perimeter() {
        return 4 * a;
    }

    @Override
    double area() {
        return a * a;
    }
}
