package ru.progwards.java1.lessons.queues;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private double sum;
    private int num;

    // статический список для автонумерации
    static List<Order> listOrder = new ArrayList<>();

    public Order(double sum) {
        this.sum = sum;
        /* для реализации автонумерации будем добавлять каждый экземпляр класса в список,
        * а затем num будет равен индексу в списке + 1, т.к. нам нужна нумерация с 1 */
        listOrder.add(this);
        num = listOrder.indexOf(this) + 1;
    }

    public double getSum() {
        return sum;
    }

    public int getNum() {
        return num;
    }

    @Override
    public String toString() {
        return "Order{" +
                "sum=" + sum +
                ", num=" + num +
                '}';
    }
}
