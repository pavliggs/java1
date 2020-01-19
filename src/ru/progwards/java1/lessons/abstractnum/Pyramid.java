package ru.progwards.java1.lessons.abstractnum;

public class Pyramid extends Figure3D {

    public Pyramid(Number segment) {
        super(segment);
    }

    @Override
    public Number volume() {
        if (segment.getClass() == IntNumber.class)
            return segment.mul(segment.mul(segment).div(new IntNumber(3)));
        else
            return segment.mul(segment.mul(segment).div(new DoubleNumber(3)));
    }
}
