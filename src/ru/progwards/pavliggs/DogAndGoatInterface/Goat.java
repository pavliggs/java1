package ru.progwards.pavliggs.DogAndGoatInterface;

public class Goat implements Speaking, Eating {
    @Override
    public String say() {
        return "Мее";
    }

    @Override
    public String eat() {
        return "Сено";
    }
}
