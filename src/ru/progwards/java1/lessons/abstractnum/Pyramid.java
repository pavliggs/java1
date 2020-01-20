package ru.progwards.java1.lessons.abstractnum;

public class Pyramid extends Figure3D {

    public Pyramid(Number segment) {
        super(segment);
    }

    @Override
    public Number volume() {
        /*
        * чтобы метод правильно работал для экземпляра класса IntNumber и экземпляра класса DoubleNumber
        * необходимо задать условие и при определенном типе параметра класса Pyramid программа будем выполняться
        * нужным способом
        * */
        if (segment.getClass() == IntNumber.class)
            return segment.mul(segment.mul(segment)).div(new IntNumber(3));
        else
            return segment.mul(segment.mul(segment)).div(new DoubleNumber(3));
    }
}
