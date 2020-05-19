package ru.progwards.java2.lessons.generics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DynamicArray<T> {

    private T[] array;
    private int count;

    public DynamicArray() {
        array = (T[])new Object[10];
        count = 0;
    }

    void add(T elem) {
        // присваиваем значение конечному элементу
        array[count++] = elem;

        // если весь массив заполнен и count = array.length, то создаём новый массив с длиной вдвое большей
        if (count == array.length)
            array = increaseArraySize();
    }

    void insert(int pos, T elem) {
        try {
            /*
            * если мы вставляем значение элемента в позицию, в которой уже имееется значение, то данной позиции
            * присваивается новое значение, а остальные элементы сдвигаются на одну позицию выше
            * если pos >= count, то это значит, что данная позиция ещё не была заполнена и значит изменить её значение
            * невозможно
            * */
            if (pos < count) {
                T[] newArray = (T[])new Object[array.length];
                System.arraycopy(array, 0, newArray, 0, pos);
                newArray[pos] = elem;
                System.arraycopy(array, pos, newArray, pos + 1, array.length - pos - 1);
                array = newArray;
                ++count;

                if (count == array.length)
                    array = increaseArraySize();

            } else
                throw new ArrayIndexOutOfBoundsException();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Позиции " + pos + " в массиве не существует");
        }
    }

    void remove(int pos) {
        try {
            if (pos < count) {
                T[] newArray = (T[])new Object[array.length - 1];
                System.arraycopy(array, 0, newArray, 0, pos);
                System.arraycopy(array, pos + 1, newArray, pos, array.length - pos - 1);
                array = newArray;
            } else
                throw new ArrayIndexOutOfBoundsException();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Позиции " + pos + " в массиве не существует");
        }
    }

    T get(int pos) {
        return array[pos];
    }

    int size() {
        return array.length;
    }

    /*
    * метод создаёт новый массив с длиной вдвое большей
    * копирует туда все значения массива array
    * затем array присваиваем новый массив
    * */
    T[] increaseArraySize() {
        T[] newArray = (T[])new Object[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }

    public static void main(String[] args) {
        DynamicArray<Integer> obj = new DynamicArray<>();

        obj.add(5);
        obj.add(4);
        obj.add(3);
        obj.add(2);
        obj.add(1);
        obj.insert(3, 77);
        obj.insert(4, 19);
    }
}
