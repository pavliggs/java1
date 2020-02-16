package ru.progwards.java1.lessons.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator<T> implements Iterator<T> {
    private T[][] array;
    private int i, j = 0;

    MatrixIterator(T[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return i < array.length && j < array[i].length;
    }

    @Override
    public T next() {
        if (j < array[i].length) {
            if (j == array[i].length - 1) {
                j = 0;
                int x = i;
                return array[i++][j + array[x].length - 1];
            } else
                return array[i][j++];
        } else
            throw new NoSuchElementException("No such element.");
    }

    public static void main(String[] args) {
        Integer[][] arr = {{1, 3, 9, 100}, {4, 5, 13}, {10, 7, 6}};
        MatrixIterator<Integer> matrixArr = new MatrixIterator<>(arr);

        while (matrixArr.hasNext()) {
            Integer intObj = matrixArr.next();
            System.out.print(intObj + " ");
        }
    }
}
