package ru.progwards.java2.lessons.generics;

import java.util.ArrayList;
import java.util.List;

public class FruitBox<T extends FruitBox> {

    private List<T> list;

    public FruitBox() {
        list = new ArrayList<>();
    }

    // добавляем фрукты в корзину
    void add(T... elem) {
        for (int i = 0; i < elem.length; i++) {
            list.add(elem[i]);
        }
    }

    // получаем вес корзины с фруктами
    double getWeight() {
        double weight = 0;
        for (int i = 0; i < list.size(); i++) {
            weight += list.get(i).getWeight();
        }
        return weight;
    }

    void moveTo(FruitBox fruitBox) {
        try {
            if (this.list.isEmpty()) {
                System.out.println("Нельзя переложить фрукты из пустой корзины!");
                return;
            }
            try {
                if (this.getElement().getClass().equals(fruitBox.getElement().getClass())) {
                    // если фрукты в обоих корзинах равны, то пересыпаем фрукты из this в fruitBox
                    shiftToAndClear(fruitBox);
                } else {
                    throw new UnsupportedOperationException();
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Корзина " + fruitBox + " пустая!");
            }

        } catch(UnsupportedOperationException e) {
            System.out.println("Содержимое корзин не соответствует одному типу");
        }
    }

    // метод сравнивает вес корзин
    int compareTo(FruitBox fruitBox) {
        if (this.getWeight() > fruitBox.getWeight())
            return 1;
        if (this.getWeight() < fruitBox.getWeight())
            return -1;
        return 0;
    }

    // метод перемещает фрукты из одной корзины в другую
    void shiftToAndClear(FruitBox fruitBox) {
        for (int i = 0; i < this.list.size(); i++) {
            fruitBox.add(this.list.get(i));
        }
        this.list.clear();
    }

    // получить 0-й элемент list
    T getElement() {
        T elem = list.get(0);
        return elem;
    }

    public static void main(String[] args) {
        FruitBox<Apple> fruitBox1 = new FruitBox<>();
        FruitBox<Orange> fruitBox2 = new FruitBox<>();
        FruitBox<Apple> fruitBox3 = new FruitBox<>();
        fruitBox1.add(new Apple());
        fruitBox2.add(new Orange(), new Orange());
        fruitBox3.add(new Apple(), new Apple());
        fruitBox1.moveTo(fruitBox3);
        System.out.println(fruitBox3.getWeight());

        System.out.println(fruitBox2.compareTo(fruitBox3));
    }
}
