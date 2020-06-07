package ru.progwards.pavliggs.java2.L7;

public class ReflectionExample {

    public int number;
    private String name;

    ReflectionExample() { }

    ReflectionExample(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public void changeNumber(int value) {
        number += value;
    }

    public void changeNumber() {
        number += 70;
    }

    @Override
    public String toString() {
        return "ReflectionExample{" +
                "number=" + number +
                ", name='" + name + '\'' +
                '}';
    }
}
