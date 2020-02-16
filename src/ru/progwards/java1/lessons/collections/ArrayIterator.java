package ru.progwards.java1.lessons.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<T> implements Iterator<T> {
    private T[] array;
    private int index = 0;

    ArrayIterator(T[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return index < array.length;
    }

    @Override
    public T next() {
        if (index < array.length) {
            // выводим array[index], а затем index увеличивается на 1
            return array[index++];
        }
        else
            throw new NoSuchElementException("No such element.");
    }

    public static void main(String[] args) {
        ArrayIterator<Integer> arr = new ArrayIterator<>(new Integer[]{1, 1, 1, 3, 5});

        while (arr.hasNext()) {
            Integer intObj = arr.next();
            System.out.println(intObj);
        }
    }
}
