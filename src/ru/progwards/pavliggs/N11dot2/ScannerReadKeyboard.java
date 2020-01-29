package ru.progwards.pavliggs.N11dot2;

import java.util.Scanner;

public class ScannerReadKeyboard {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите Имя:");
            String firstName = scanner.nextLine();
            System.out.println("Введите Фамилию:");
            String lastName = scanner.nextLine();
            System.out.println("Введите Возраст:");
            String age = scanner.nextLine();

            System.out.println(firstName);
            System.out.println(lastName);
            System.out.println(age);
        }
    }
}
