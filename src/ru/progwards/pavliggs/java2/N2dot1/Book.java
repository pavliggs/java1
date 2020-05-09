package ru.progwards.pavliggs.java2.N2dot1;

public class Book {
    String name;
    String author;
    double price;

    public Book(String name, String author, double price) {
        this.name = name;
        this.author = author;
        this.price = price;
    }

    @Override
    public String toString() {
        return author + ", " + name + ", " + price;
    }
}
