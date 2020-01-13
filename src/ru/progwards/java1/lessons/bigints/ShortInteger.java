package ru.progwards.java1.lessons.bigints;

public class ShortInteger extends AbsInteger {
    short a;

    ShortInteger(short a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return Integer.toString(a);
    }
}
