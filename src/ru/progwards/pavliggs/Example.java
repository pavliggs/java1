package ru.progwards.pavliggs;

import ru.progwards.java2.lessons.recursion.HanoiTower;

import java.util.*;

public class Example {
    private int size;
    private int pos;
    private int quantityPintles;
    private List<Deque<String>> pintles;

    public Example(int size, int pos) {
        this.size = size;
        this.pos = pos;
        quantityPintles = 3;
        pintles = new ArrayList<>();

        // добавляем штыри в список pintles и заполняем штырь pos элементами
        for (int i = 0; i < quantityPintles; i++) {
            pintles.add(new ArrayDeque<>());
            if (i == pos) {
                for (int j = size; j >= 1; j--)
                    pintles.get(i).push("<00" + j + ">");
            }
        }
    }

    public void move(int from, int to) {

    }

    // текущее состояние башни
    void print() {
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

    String[] createArray(Deque<String> deque) {
        String[] array = new String[size];
        int i = 0;
        // очередь НЕ ПУСТАЯ
        if (!deque.isEmpty()) {
            if (deque.size() == size) {
                for (String str : deque)
                    array[i++] = str;
            } else {
                int count = 0;
                for (String str : deque) {
                    ++count;
                    if (count > (deque.size() - size))
                        array[i++] = str;
                    else
                        array[i++] = "  I  ";
                }
            }
        } else {
            Arrays.fill(array, "  I  ");
        }
        return array;
    }

    Integer getPos(int num1, int num2) {
        Set<Integer> set2 = Set.of(num1, num2);
        Set<Integer> result = new HashSet<>(Set.of(0, 1, 2));
        result.removeAll(set2);
        Integer num = 0;
        for (Integer elem : result)
            num = elem;
        return num;
    }

    public static void main(String[] args) {
        Example tower = new Example(3, 0);
        tower.print();
    }
}
