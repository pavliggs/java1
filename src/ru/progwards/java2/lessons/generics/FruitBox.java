package ru.progwards.java2.lessons.generics;

import java.util.ArrayList;
import java.util.List;

public class FruitBox<T extends FruitBox> {

    private List<T> list;

    public FruitBox() {
        list = new ArrayList<>();
    }

    void add(T... elem) {
        for (int i = 0; i < elem.length; i++) {
            list.add(elem[i]);
        }
    }

    double getWeight() {
        double weight = 0;
        for (int i = 0; i < list.size(); i++) {
            weight += list.get(i).getWeight();
        }
        return weight;
    }

    public static void main(String[] args) {
        FruitBox<Apple> fruitBox1 = new FruitBox<>();
        FruitBox<Orange> fruitBox2 = new FruitBox<>();
        fruitBox1.add(new Apple(), new Apple(), new Apple());
        System.out.println(fruitBox1.getWeight());
    }
}
