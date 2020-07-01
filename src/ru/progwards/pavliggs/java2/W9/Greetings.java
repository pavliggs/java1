package ru.progwards.pavliggs.java2.W9;

import java.lang.reflect.Method;

public class Greetings {
    @AnnotationTest(text = "Hello, world!")
    void hello() {}

    @AnnotationTest(text = "Good day!")
    void goodday() {}

    @AnnotationTest(text = "Good night!")
    void goodnight() {}

    @AnnotationTest(text = "Hi, world!")
    void hi() {};

    void printAnnotation() {
        Method[] methods = Greetings.class.getDeclaredMethods();
        for (Method m : methods) {
            if (m.isAnnotationPresent(AnnotationTest.class)) {
                AnnotationTest annotation = m.getAnnotation(AnnotationTest.class);
                System.out.println(m.getName() + " " + annotation.text());
            }
        }
    }

    public static void main(String[] args) {
        Greetings gr = new Greetings();
        gr.printAnnotation();
    }
}
