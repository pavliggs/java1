package ru.progwards.java1.lessons.queues;

import java.util.ArrayDeque;
import java.util.Deque;

public class StackCalc {
    static Deque<Double> stack = new ArrayDeque<>();

    public void push(double value) {
        stack.push(value);
    }

    public double pop() {
        return stack.pop();
    }

    public void add() {
        Double value = pop() + pop();
        push(value);
    }

    public void sub() {
        Double value1 = pop();
        Double value2 = pop();
        Double result = value2 - value1;
        push(result);
    }

    public void mul() {
        Double value = pop() * pop();
        push(value);
    }

    public void div() {
        Double value1 = pop();
        Double value2 = pop();
        Double result = value2 / value1;
        push(result);
    }
}
