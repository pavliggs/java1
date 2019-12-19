package ru.progwards.java1.lessons.classes;

public class Hamster extends Animal{

    public Hamster(double weight) {
        super(weight);
    }

    @Override
    public AnimalKind getKind() {
        AnimalKind hamster = AnimalKind.HAMSTER;
        return hamster;
    }

    @Override
    public FoodKind getFoodKind() {
        FoodKind corn = FoodKind.CORN;
        return corn;
    }

    @Override
    public double getFoodCoeff() {
        return 0.03;
    }
}
