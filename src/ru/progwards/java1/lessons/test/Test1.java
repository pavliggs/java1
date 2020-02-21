package ru.progwards.java1.lessons.test;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class Test1 {
    public static void main(String[] args) {
        try(FileReader fileReader = new FileReader("letter.txt")) {
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextLine()) {
                String str = scanner.next();
                System.out.println(str);
            }
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
