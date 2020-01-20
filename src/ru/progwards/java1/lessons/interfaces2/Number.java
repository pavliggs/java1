package ru.progwards.java1.lessons.interfaces2;

public abstract class Number implements Comparable<Number> {
    @Override
    public abstract int compareTo(Number o);

    public abstract Number mul(Number num);

    public abstract Number div(Number num);

    public abstract Number newNumber(String strNum);

    @Override
    public abstract String toString();
}
