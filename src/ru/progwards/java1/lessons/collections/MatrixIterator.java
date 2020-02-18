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
        if (i == array.length)
            throw new NoSuchElementException("No such element.");
        if (j == array[i].length - 1) {
            // сохраняем текущее значение j
            int saveValueJ = j;
            // обнуляем
            j = 0;
            return array[i++][saveValueJ];
        } else
            return array[i][j++];
    }

    public static void main(String[] args) {
        Integer[][] arr = {{1, 3, 9}, {4, 5, 13}, {10, 7, 6}};
//        Integer[][] arr = {{1, 3, 9, 100}};
        MatrixIterator<Integer> matrixArr = new MatrixIterator<>(arr);

        while (matrixArr.hasNext()) {
            Integer intObj = matrixArr.next();
            System.out.print(intObj + " ");
        }
//        System.out.println(matrixArr.next());
//        System.out.println(matrixArr.next());
//        System.out.println(matrixArr.next());
//        System.out.println(matrixArr.next());
//        System.out.println(matrixArr.next());
    }
}
