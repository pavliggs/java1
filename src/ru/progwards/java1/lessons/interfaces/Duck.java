package ru.progwards.java1.lessons.interfaces;

public class Duck extends Animal {

    public Duck(double weight) {
        super(weight);
    }

    @Override
    public AnimalKind getKind() {
        AnimalKind duck = AnimalKind.DUCK;
        return duck;
    }

    @Override
    public FoodKind getFoodKind() {
        FoodKind corn = FoodKind.CORN;
        return corn;
    }

    @Override
    public double getFoodCoeff() {
        return 0.04;
    }
}
