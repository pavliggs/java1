package ru.progwards.java1.lessons.classes;

public class Animal {
    double weight;

    enum AnimalKind {
        ANIMAL,
        COW,
        HAMSTER,
        DUCK,
    }

    enum FoodKind {
        UNKNOWN,
        HAY,
        CORN,
    }

    public Animal(double weight) {
        this.weight = weight;
    }

    public AnimalKind getKind() {
        AnimalKind animal = AnimalKind.ANIMAL;
        return animal;
    }

    public FoodKind getFoodKind() {
        FoodKind unknown = FoodKind.UNKNOWN;
        return unknown;
    }

    public double getWeight() {
        return weight;
    }

    public double getFoodCoeff() {
        return 0.02;
    }

    public double calculateFoodWeight() {
        return getWeight() * getFoodCoeff();
    }

    @Override
    public String toString() {
        return "I am " + getKind() + ", eat " +  getFoodKind();
    }

    public String toStringFull() {
        return "I am " + getKind() + ", eat " +  getFoodKind() + " " + calculateFoodWeight();
    }
}
