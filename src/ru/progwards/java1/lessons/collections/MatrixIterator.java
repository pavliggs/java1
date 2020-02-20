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
        /* после того как мы возвратим последний элемент в последней строке i увеличится на 1, что не совсем корректно
         * если не использовать метод hasNext(), то программа вызовет исключение */
        if (i == array.length)
            throw new NoSuchElementException("No such element.");
        if (j == array[i].length - 1) {
            // сохраняем текущее значение j
            int saveValueJ = j;
            // обнуляем j, т.к. следующий элемент должен быть с нулевым индексом
            j = 0;
            /* возвращаем последний элемент в строке за счет переменной saveValueJ, а после return
            * i увеличится на 1, что гарантирует нам переход на следующую строку */
            return array[i++][saveValueJ];
        } else
            /* в самый первый вызов метода будет возвращен элемент array[0][0], а за счёт того, что я использую
            * постфиксный инкремент, то j будет увеличиваться на 1 после return */
            return array[i][j++]; //
    }

    public static void main(String[] args) {
        Integer[][] arr = {{1, 3, 9}, {4, 5, 13}, {10, 7, 6}};
        MatrixIterator<Integer> matrixArr = new MatrixIterator<>(arr);

        while (matrixArr.hasNext()) {
            Integer intObj = matrixArr.next();
            System.out.print(intObj + " ");
        }
    }
}
