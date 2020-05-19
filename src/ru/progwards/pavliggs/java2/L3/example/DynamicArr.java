package ru.progwards.pavliggs.java2.L3.example;

import ru.progwards.java2.lessons.generics.DynamicArray;

import java.util.Arrays;

public class DynamicArr<T> {

    private T[] array;
    private int count;

    public DynamicArr() {
        array = (T[])new Object[10];
        count = 0;
    }

    void add(T elem) {
//        offsetValue();
        // присваиваем значение конечному элементу
        array[count++] = elem;

        /*
         * если весь массив заполнен и count = 10, то создаём новый массив с длиной вдвое большей
         * если count < 10, то присваиваем count 10, чтобы значения всегда добавлялись в конец массива
         * копируем туда все значения массива array
         * затем array присваиваем новый массив
         * */
        if (getCounter() == array.length) {
            if (count < array.length)
                count = array.length;
            array = increaseArraySize();
        }
        System.out.println(Arrays.toString(array));
    }

    void insert(int pos, T elem) {
        try {
            /*
             * если мы вставляем значение элемента в позицию, в которой уже имееется значение, то данной позиции
             * присваивается новое значение, а остальные элементы сдвигаются на одну позицию выше
             * если значение элемента в pos = null, то этому элементу присваивается значение elem
             * */
            if (pos != array.length - 1 && array[pos] != null) {
                T[] newArray = (T[])new Object[array.length];
                System.arraycopy(array, 0, newArray, 0, pos);
                newArray[pos] = elem;
                if (pos < count) {
                    System.arraycopy(array, pos, newArray, pos + 1, count - pos);
                    int posNotNull = getOtherPos();
                    if (posNotNull > 0)
                        System.arraycopy(array, posNotNull, newArray, posNotNull, array.length - posNotNull);
                    ++count;
                } else
                    System.arraycopy(array, pos, newArray, pos + 1, array.length - pos - 1);
                array = newArray;
            } else
                array[pos] = elem;

            // если массив заполнен значениями не равными null, то увеличиваем размер массива в 2 раза
            if (getCounter() == array.length) {
                array = increaseArraySize();
            }

            System.out.println(Arrays.toString(array));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Позиции " + pos + " в массиве не существует");
        }
    }

    void remove(int pos) {
        T[] newArray = (T[])new Object[array.length - 1];
        System.arraycopy(array, 0, newArray, 0, pos);
        System.arraycopy(array, pos + 1, newArray, pos, array.length - pos - 1);
        array = newArray;
        System.out.println(Arrays.toString(array));
    }

    T get(int pos) {
        return array[pos];
    }

    int size() {
        return array.length;
    }

    // метод возвращает индекс первого элемента после count не равного null
    int getOtherPos() {
        int pos = 0;
        for (int i = count; i < array.length; i++) {
            if (array[i] != null) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    // метод увеличивет размер массива в 2 раза
    T[] increaseArraySize() {
        T[] newArray = (T[])new Object[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }

    // метод считает элементы, значения которых не равны null
    private int getCounter() {
        int count = 0;

        for (T elem : array) {
            if (elem != null)
                ++count;
        }

        return count;
    }

    public static void main(String[] args) {
        DynamicArr<Integer> obj = new DynamicArr<>();
        System.out.println(Arrays.toString(obj.array));

        obj.add(5);
        obj.add(4);
        obj.add(3);
        obj.add(2);
        obj.add(1);
        obj.insert(3, 77);
//        obj.insert(9, 1213);
        obj.insert(9, 121);
        obj.insert(6, 87);
        obj.insert(7, 77);


//
//        obj.add(-4);
//        obj.add(10);
//        obj.add(111);
//
        obj.remove(7);
    }
}
