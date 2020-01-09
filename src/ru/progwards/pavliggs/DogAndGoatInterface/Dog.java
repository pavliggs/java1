package ru.progwards.pavliggs.DogAndGoatInterface;

public class Dog implements Eating, Speaking{
    @Override
    public String say() {
        return "Гав";
    }

    @Override
    public String eat() {
        return "Мясо";
    }
}
