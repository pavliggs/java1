package ru.progwards.pavliggs;

public class Calculator {
    private int result;

    public Calculator() {
        result = 0;
    }

    public int set(int num) {
        return result = num;
    }

    public int add(int num) {
        return result += num;
    }

    public int sub(int num) {
        return result -= num;
    }

    public int getResult() {
        return result;
    }
}
