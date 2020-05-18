package ru.progwards.java2.lessons.recursion;

import java.util.*;

public class HanoiTower {
    private int size;
    private int pos;
    private int quantityPintles;
    private List<Deque<String>> pintles;
    private boolean onTrace = false;

    public HanoiTower(int size, int pos) {
        this.size = size;
        this.pos = pos;
        quantityPintles = 3;
        pintles = new ArrayList<>();

        // добавляем штыри в список pintles
        for (int i = 0; i < quantityPintles; i++) {
            pintles.add(new ArrayDeque<>());
            // заполняем штырь с позицией pos кольцами 3, 2, 1
            if (i == pos) {
                for (int j = size; j >= 1; j--)
                    pintles.get(i).push("<00" + j + ">");
            }
        }
    }

    public void move(int from, int to, int size) {
        if (size == 1) {
            moveOneRing(from, to);
            if (onTrace)
                print();
            return;
        }

        int otherPintle = otherPintle(from, to);

        move(from, otherPintle, size - 1);
        moveOneRing(from, to);
        if (onTrace)
            print();
        move(otherPintle, to, size - 1);
    }

    // текущее состояние башни
    void print() {
        /*
        * при помощи метода createArray преобразуем очереди в массив из 3х элементов
        * сначала выводим 0-вые элементы массивов в одну строку, затем 1-ые и т.д. в зависимости от размера башни size
        * */
        for (int i = 0; i < size; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < pintles.size(); j++) {
                String[] array = createArray(pintles.get(j));
                stringBuilder.append(array[i] + " ");
            }
            System.out.println(stringBuilder.toString());
        }
        System.out.println("=================");
    }

    void setTrace(boolean on) {
        onTrace = on;
    }

    // метод вычисляет вспомогательный штырь
    int otherPintle(int from, int to) {
        return 3 - from - to;
    }

    // метод перекладывает одно верхнее кольцо со штыря from на to
    void moveOneRing(int from, int to) {
        String elem = pintles.get(from).pop();
        pintles.get(to).push(elem);
    }

    String[] createArray(Deque<String> deque) {
        String[] array = new String[size];
        // если deque пустая, то заполняем массив символом I
        if (deque.isEmpty())
            Arrays.fill(array, "  I  ");
        else {
            int count = 0;
            // difference - разница между размером пирамиды и размером deque
            int difference = size - deque.size();
            for (int i = 0; i < array.length; i++) {
                ++count;
                /*
                * будем копировать элементы очереди в массив
                * если размер очеред меньше размера массива, то заполняем остальные элементы массива символом I
                * */
                if (count > difference) {
                    for (String str : deque)
                        array[i++] = str;
                    break;
                } else
                    array[i] = "  I  ";
            }
        }

        return array;
    }

    public static void main(String[] args) {
        HanoiTower tower = new HanoiTower(4, 0);
        tower.print();
        tower.setTrace(true);
        tower.move(0, 1, 4);
    }
}
