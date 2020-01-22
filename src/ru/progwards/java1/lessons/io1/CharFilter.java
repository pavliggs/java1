package ru.progwards.java1.lessons.io1;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class CharFilter {
    public static void filterFile(String inFileName, String outFileName, String filter) {
        try {
            FileReader reader = new FileReader(inFileName);
            try {
                Scanner scanner = new Scanner(reader);
                while (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
            } finally {
                reader.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
