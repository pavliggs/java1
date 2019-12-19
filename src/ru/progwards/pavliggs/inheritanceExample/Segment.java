package ru.progwards.pavliggs.inheritanceExample;

public class Segment extends Figure {
    double a;

    Segment(double a) {
        this.a = a;
    }

    //переопределение метода родителя
    @Override
    double perimeter() {
        return a;
    }

    @Override
    public String toString() {
        return "Отрезок длиной " + a;
    }
}

