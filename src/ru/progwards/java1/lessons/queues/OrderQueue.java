package ru.progwards.java1.lessons.queues;

import java.util.Comparator;
import java.util.PriorityQueue;

public class OrderQueue {
    // компаратор для сортировки объектов типа Order в очереди PriorityQueue по номеру заказа
    static Comparator<Order> comparator = new Comparator<>() {
        @Override
        public int compare(Order o1, Order o2) {
            return Integer.compare(o1.getNum(), o2.getNum());
        }
    };

    // очереди для заказов различных по сумме заказа
    static PriorityQueue<Order> class1 = new PriorityQueue<>(comparator);
    static PriorityQueue<Order> class2 = new PriorityQueue<>(comparator);
    static PriorityQueue<Order> class3 = new PriorityQueue<>(comparator);

    // в зависимости от суммы заказа добавляем объект в ту или иную очередь
    public void add(Order order) {
        if (order.getSum() > 20_000)
            class1.add(order);
        if (order.getSum() > 10_000 && order.getSum() <= 20_000)
            class2.add(order);
        if (order.getSum() <= 10_000)
            class3.add(order);
    }

    /* получаем заказы в зависимости от их приоритета: самые дорогие заказы с наименьшим номером заказа
     * обрабатываются в первую очередь */
    public Order get() {
        if (!class1.isEmpty())
            return class1.poll();
        else if (!class2.isEmpty())
            return class2.poll();
        else if (!class3.isEmpty())
            return class3.poll();
        else
            return null;
    }

    public static void main(String[] args) {
//        Order order1 = new Order(140.6);
        Order order2 = new Order(23.6);
        Order order3 = new Order(23415);
        Order order4 = new Order(40000.8);

//        System.out.println(order2.getNum());

        OrderQueue orderQueue = new OrderQueue();
        orderQueue.add(order3);
        orderQueue.add(order2);
        orderQueue.add(order4);

        System.out.println(new OrderQueue().get());
        System.out.println(new OrderQueue().get());
        System.out.println(new OrderQueue().get());
    }
}
