package ru.progwards.pavliggs.inheritanceExample;

public class Square extends Segment {
    /*
    необходимо при помощи super вызвать конструктор родителя ЯВНО
    переменную "a" возьмем от родителя
    */
    Square(double a) {
        super(a);
    }

    @Override
    double perimeter() {
        return 4 * a;
    }

    @Override
    double area() {
        return a * a;
    }

    @Override
    public String toString() {
        return "Квадрат со стороной " + a;
    }
}
