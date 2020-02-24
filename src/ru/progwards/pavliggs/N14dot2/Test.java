package ru.progwards.pavliggs.N14dot2;

import java.util.ArrayDeque;

public class Test {
    int sumLastAndFirst(ArrayDeque<Integer> deque) {
        if (deque.isEmpty())
            return 0;
        int result = deque.peekFirst() + deque.peekLast();
        return result;
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        for (int i = 3; i < 10; i++) {
            arrayDeque.offer(i);
        }
        System.out.println(new Test().sumLastAndFirst(arrayDeque));
    }
}
