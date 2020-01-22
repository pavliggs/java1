package ru.progwards.pavliggs.N10dot2;

import java.io.FileWriter;
import java.io.IOException;

public class Exception {

    public static void createFile(String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName, false);
        writer.close();
    }

    public static void createFile2(String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName, false);
            try {
                writer.write("Записать что-то...");
            } catch (IOException e) {
                System.out.println(e);
            } finally {
                writer.close();//этот вариант правильный, т.к. метод всегда выполнится
            }
            writer.close(); //этот метод может не выполниться, что может привести к неправильной работе программы
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void createFile3(String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName, false);
        } catch (IOException e) {
            throw new RuntimeException("Невозможно создать файл с именем " + fileName);
        }
    }

    public static void main(String[] args) {
        try {
            createFile("???");
        } catch (IOException e) {
            System.out.println("Некорректное имя файла!!!");
        }

        createFile2("???");

        try {
            createFile3("???");
        } catch (RuntimeException e) {
            System.out.println();
        }
    }
}
