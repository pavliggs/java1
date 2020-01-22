package ru.progwards.java1.lessons.io1;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LineCount {
    public static int calcEmpty(String fileName) {
        int i = 0;
        try {
            FileReader reader = new FileReader(fileName);
            try {
                Scanner scanner = new Scanner(reader);
                while (scanner.hasNextLine()) {
                    String strScanner = scanner.nextLine();
                    //если сторока пустая, то увеличим i на 1
                    if (strScanner.isEmpty())
                        i++;
                }
                return i;
            } finally {
                reader.close();
            }
        } catch (IOException e) {
            return -1;
        }
    }

    public static void main(String[] args) {
        System.out.println(calcEmpty("file1.txt"));
    }
}
