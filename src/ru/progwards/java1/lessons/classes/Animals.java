package ru.progwards.java1.lessons.classes;

public class Animals {

    public static void animalInfo(Animal animal) {
        System.out.println(animal.toStringFull());
    }

    public static void main(String[] args) {
        Cow cow = new Cow(500);
        Hamster hamster = new Hamster(0.2);
        Duck duck = new Duck(5);

        animalInfo(cow);
        animalInfo(hamster);
        animalInfo(duck);
    }
}
