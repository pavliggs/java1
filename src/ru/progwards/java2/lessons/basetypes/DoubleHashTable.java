package ru.progwards.java2.lessons.basetypes;

import org.telegram.telegrambots.meta.api.methods.groupadministration.DeleteChatStickerSet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DoubleHashTable<K extends HashValue,V> {
    private static final double A = 0.6180339887;
    private static final int N = 13;

    static class Node<K extends HashValue,V> {
        private final K key;
        private V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    private static final long UINT_MAX = 4294967295L;

    private Node[] table;

    DoubleHashTable() {
        table = new Node[101];
    }

    public void add(K key, V value) {
        int index = getHash(key);
        Node<K, V> node = new Node<>(key, value);
        if (table[index] == null || table[index].getKey().equals(key)) {
            table[index] = node;
        } else {
            // вычисляем шаг, с которым будем идти по массиву
            int n = getHashStep(key);
            // считаем колличество коллизий
            int count = 0;
            // пока элементы с индексом index + n заняты другими значениями - продолжаем поиск
            // проходимся по массиву делая полный круг и если не коллизии превысили 10%, то увеличиваем массив
            for (int i = index + n; ; i += n) {
                index = i % table.length;
                if (table[index] == null) {
                    table[index] = node;
                    break;
                }
                ++count;
                // если колличество коллизий больше 10%, то увеличиваем размер таблицы
                if (count > (table.length / 10)) {
                    table = increaseArrayLenght();
                    add(key, value);
                    return;
                }
            }
        }
    }

    public V get(K key) {
        int index = getHash(key);
        if (table[index] == null)
            return null;
        if (table[index].getKey().equals(key))
            return (V) table[index].getValue();
        int n = getHashStep(key);
        index += n;

        for (int i = index; i < table.length; i += n) {
            if (table[i] != null && table[i].getKey().equals(key)) {
                return (V) table[index].getValue();
            }
        }

        return null;
    }

    public int size() {
        return table.length;
    }

    public Iterator<Node<K,V>> getIterator() {
        return new Iterator<>() {
            private int indx = 0;
            private Node<K,V> lastReturned;
            private Node<K,V> current = table[indx];

            @Override
            public boolean hasNext() {
                return indx < table.length;
            }

            @Override
            public Node<K, V> next() {
                if (!hasNext())
                    throw new NullPointerException();

                lastReturned = current;
                ++indx;
                current = table[indx];
                return lastReturned;
            }
        };
    }

    public void printHashTable() {
        System.out.println(Arrays.toString(table));
    }

    // метод преобразует ключ в индекс массива
    private int getHash(K key) {
        return key.getHash() % table.length;
    }

    /*
    * метод преобразует ключ в значение шага, с которым будем проходить по массиву, пока не найдём значение элемента
    * равное null
    *  */
    private int getHashStep(K key) {
        double d = A * key.getHash();
        return (int)(N * (d % 1));
    }

    // метод определяет простое число или нет
    private boolean isSimple(int num) {
        for (int i = 2; i < num; i++) {
            if (num % i == 0)
                return false;
        }
        return true;
    }

    // метод находит простое число > num
    private int getSimpleUp(int num) {
        for (int i = 1; i < num; i++) {
            int value = num + i;
            if (isSimple(value)) {
                if (i == 1)
                    return 1;
                return value;
            }
        }
        return 0;
    }

    // метод находит простое число < num
    private int getSimpleDown(int num) {
        for (int i = 1; i < num; i++) {
            int value = num - i;
            if (isSimple(value)) {
                if (i == 1)
                    return 1;
                return value;
            }
        }
        return 0;
    }

    // метод ищет ближайшее простое число к num или выводит num, если оно простое
    private int searchNearSimpleNum(int num) {
        if (isSimple(num))
            return num;

        int resultUp = getSimpleUp(num);
        if (resultUp == 1)
            return num + resultUp;
        int resultDown = getSimpleDown(num);
        if (resultDown == 1)
            return num - resultDown;

        // сравниваем близость до num - какое число ближе то и возвращаем
        if ((resultUp - num) < (num - resultDown))
            return resultUp;
        if ((resultUp - num) > (num - resultDown))
            return resultDown;
        return resultUp;
    }

    // метод увеличивает массив в два раза, но с учётом того, что длина нового массива должна быть простым числом
    private Node[] increaseArrayLenght() {
        int lenght = searchNearSimpleNum(table.length * 2);
        Node[] newTable = Arrays.copyOf(table, lenght);
//        System.arraycopy(table, 0, newTable, 0, table.length);
        return newTable;
    }

    public static void main(String[] args) {
        DoubleHashTable<IntHashKey, Integer> doubleHashTable = new DoubleHashTable<>();
        doubleHashTable.printHashTable();

        for (int i = 377; i < 50000; i++) {
            doubleHashTable.add(new IntHashKey(i), i);
        }

        doubleHashTable.add(new IntHashKey(1023), 12);

        doubleHashTable.printHashTable();
        System.out.println(doubleHashTable.size());

        System.out.println(doubleHashTable.get(new IntHashKey(478)));
    }
}
