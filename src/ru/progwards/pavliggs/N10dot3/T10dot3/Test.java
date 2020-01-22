package ru.progwards.pavliggs.N10dot3.T10dot3;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Test {
    private int lineCount(String filename) throws IOException {
        int i = 0;
        try {
            FileReader reader = new FileReader(filename);
            try {
                Scanner scanner = new Scanner(reader);
                while (scanner.hasNextLine()) {
                    scanner.nextLine();
                    i++;
                }
            } finally {
                reader.close();
            }
        } catch (IOException e) {
            throw new IOException("файл не найден");
        }
        return i;
    }

    public static void main(String[] args) {
        try {
            System.out.println(new Test().lineCount("file1.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
