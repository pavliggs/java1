package ru.progwards.java1.lessons.interfaces;

public class Cow extends Animal{

    public Cow(double weight) {
        super(weight);
    }

    @Override
    public AnimalKind getKind() {
        AnimalKind cow = AnimalKind.COW;
        return cow;
    }

    @Override
    public FoodKind getFoodKind() {
        FoodKind hay = FoodKind.HAY;
        return hay;
    }

    @Override
    public double getFoodCoeff() {
        return 0.05;
    }
}
