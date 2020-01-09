package ru.progwards.pavliggs.abstractClasses;

abstract class Figure3D extends Figure {
    double a;

    public Figure3D(double a) {
        this.a = a;
    }

    @Override
    double perimeter() {
        return a;
    }
}

