package ru.progwards.pavliggs;

public class Person {
    private String name;
    private int age;
    private String country;

    public Person() {
        country = "RU";
    }

    public Person(String name, int age) {
        this();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCountry() {
        return country;
    }

    public static void main(String[] args) {
        Person person1 = new Person("Павел", 29);
        Person person2 = new Person("Мария", 27);
        Person person3 = new Person("Кирилл", 1);

        System.out.println("Имя папы - " + person1.getName());
        System.out.println("Его возраст - " + person1.getAge() + " лет");
        System.out.println("Его гражданство - " + person1.getCountry());

        System.out.println("Имя мамы - " + person2.getName());
        System.out.println("Её возраст - " + person2.getAge() + " лет");
        System.out.println("Её гражданство - " + person2.getCountry());

        System.out.println("Имя сыночка - " + person3.getName());
        System.out.println("Его возраст - " + person3.getAge() + " год");
        System.out.println("Его гражданство - " + person3.getCountry());
    }
}
