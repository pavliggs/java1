package ru.progwards.java1.lessons.interfaces2;

public class DoubleNumber extends Number implements Comparable<Number> {
    private double value;

    public DoubleNumber(double num) {
        value = num;
    }

    @Override
    public int compareTo(Number o) {
        return Double.compare(value, ((DoubleNumber)o).value);
    }

    @Override
    public Number mul(Number num) {
        return new DoubleNumber(value * ((DoubleNumber)num).value);
    }

    @Override
    public Number div(Number num) {
        return new DoubleNumber(value / ((DoubleNumber)num).value);
    }

    @Override
    public Number newNumber(String strNum) {
        return new DoubleNumber(Double.valueOf(strNum));
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
