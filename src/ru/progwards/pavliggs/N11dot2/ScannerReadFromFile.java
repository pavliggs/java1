package ru.progwards.pavliggs.N11dot2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerReadFromFile {
    public static void main(String[] args) {
        File f = new File("C:/Users/Эльдорадо/Desktop/TestJava/fileScanner.txt");

        try (Scanner scanner = new Scanner(f)) {
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                System.out.println(str);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }


    }
}
