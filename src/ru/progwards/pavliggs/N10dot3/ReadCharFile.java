package ru.progwards.pavliggs.N10dot3;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ReadCharFile {
    public static void main(String[] args) {
        int i = 0;
        try {
            FileReader reader = new FileReader("file1.txt");
            try {
                Scanner scanner = new Scanner(reader);
                while (scanner.hasNextLine()) {
                    String strFromFile = scanner.nextLine();
                    System.out.println(strFromFile);
                    if (strFromFile.isEmpty())
                        i++;
                }
            } finally {
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("file not found");
        }
        System.out.println(i);
    }
}
