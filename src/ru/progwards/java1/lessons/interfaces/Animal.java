package ru.progwards.java1.lessons.interfaces;

public class Animal implements FoodCompare, CompareWeight {
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

    public double getFood1kgPrice() {
        switch (getFoodKind()) {
            case HAY:
                return 20;
            case CORN:
                return 50;
            case UNKNOWN:
                return 0;
        }
        return 0;
    }

    public double getFoodPrice() {
        return calculateFoodWeight() * getFood1kgPrice();
    }

    @Override
    public CompareResult compareWeight(CompareWeight smthHasWeigt) {
        Animal animal = (Animal) smthHasWeigt;
        if (getWeight() < animal.getWeight())
            return CompareResult.LESS;
        else if (getWeight() == animal.getWeight())
            return CompareResult.EQUAL;
        else
            return CompareResult.GREATER;
    }

    @Override
    public int compareFoodPrice(Animal animal) {
        return Double.compare(getFoodPrice(), animal.getFoodPrice());
    }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) return true;
        if (anObject == null || getClass() != anObject.getClass())
            return false;
        Animal animal = (Animal) anObject;
        return Double.compare(getWeight(), animal.getWeight()) == 0;
    }

    @Override
    public String toString() {
        return "I am " + getKind() + ", eat " +  getFoodKind();
    }

    public String toStringFull() {
        return "I am " + getKind() + ", eat " +  getFoodKind() + " " + calculateFoodWeight();
    }

    public static void main(String[] args) {
        Cow cow1 = new Cow(700);
        Cow cow2 = new Cow(750);
        Hamster hamster = new Hamster(700);
        Duck duck1 = new Duck(700);
        Duck duck2 = new Duck(19);

//        Animal animal = new Animal(1D);
//        System.out.println(animal.getFood1kgPrice());
//        System.out.println(hamster.getFood1kgPrice());
//        System.out.println(new Cow(1D).compareFoodPrice(new Duck(1D)));
//
//        System.out.println(duck1.equals(cow2));
//        System.out.println(duck1.compareFoodPrice(duck2));

        System.out.println(cow1.compareWeight(duck1));
    }
}
