package ru.progwards.java1.lessons.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Test1 {
    public static void main(String[] args) {
        Path path = Paths.get("C:\\Users\\Эльдорадо\\Desktop\\TestJava\\text.txt");
        try {
            List<String> list = new ArrayList<>(Files.readAllLines(path));
            System.out.println(list.get(3));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
