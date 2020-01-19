package ru.progwards.java1.lessons.abstractnum;

public class DoubleNumber extends Number{
    private double value;

    public DoubleNumber(double num) {
        value = num;
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
        return new DoubleNumber(Integer.parseInt(strNum));
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
