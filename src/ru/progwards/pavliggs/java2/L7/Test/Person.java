package ru.progwards.pavliggs.java2.L7.Test;

public class Person {
    private String name;

    public Person() {
        name = "no name";
    }

    private Person(String name) {
        this.name = name;
    }

    private void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
