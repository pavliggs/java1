package ru.progwards.pavliggs.java2.N10dot1;

public class ClassLoadOrder {
    static String str = "Какая-то строка";
    static {
        System.out.println("ClassLoadOrder статический блок инициализации");
    }
}
