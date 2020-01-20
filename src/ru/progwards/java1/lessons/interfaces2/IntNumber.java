package ru.progwards.java1.lessons.interfaces2;

public class IntNumber extends Number implements Comparable<Number> {
    private int value;

    public IntNumber(int num) {
        value = num;
    }

    @Override
    public int compareTo(Number o) {
        return Integer.compare(value, ((IntNumber)o).value);
    }

    @Override
    public Number mul(Number num) {
        return new IntNumber(value * ((IntNumber)num).value);
    }

    @Override
    public Number div(Number num) {
        return new IntNumber(value / ((IntNumber)num).value);
    }

    @Override
    public Number newNumber(String strNum) {
        return new IntNumber(Integer.parseInt(strNum));
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
