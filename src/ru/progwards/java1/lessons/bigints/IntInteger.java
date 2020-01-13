package ru.progwards.java1.lessons.bigints;

public class IntInteger extends AbsInteger {
    int a;

    IntInteger(int a) {
        this.a = a;
    }

    @Override
    public long getNum() {
        return a;
    }

    @Override
    public String toString() {
        return Integer.toString(a);
    }
}
