package ru.progwards.pavliggs.java2.N4dot3;

import java.util.ArrayList;
import java.util.List;

public class BinaryHeap<T extends Comparable<T>> {

    private List<T> list = new ArrayList<>();

    public int size() {
        return list.size();
    }

    public void add(T item) {
        list.add(item);
        shiftUp();
    }

    public T poll() {
        T result = list.get(0);
        T item = list.remove(list.size() - 1);
        if (list.size() > 0) {
            list.set(0, item);
            shiftDown();
        }
        return result;
    }

    private void swap(int i, int j) {
        T item = list.get(i);
        list.set(i, list.get(j));
        list.set(j, item);
    }

    public void shiftUp() {
        int i = list.size() - 1;
        int parent = (i - 1) / 2;

        while (i > 0 && list.get(parent).compareTo(list.get(i)) < 0) {
            swap(i, parent);
            i = parent;
            parent = (i - 1) / 2;
        }
    }

    public void shiftDown() {
        int i = 0;
        boolean loop = true;

        do {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int max = i;

            if (left < list.size() && list.get(left).compareTo(list.get(max)) > 0)
                max = left;

            if (right < list.size() && list.get(right).compareTo(list.get(max)) > 0)
                max = right;

            if (max == i)
                loop = false;
            else {
                swap(i, max);
                i = max;
            }
        } while (loop);
    }

    public List<T> sort() {
        List<T> sorted = new ArrayList<>();
        for (int i = 0; size() > 0 ; i++) {
            sorted.add(poll());
        }
        return sorted;
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
