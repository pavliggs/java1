package ru.progwards.java1.lessons.io1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class CharFilter {
    public static void filterFile(String inFileName, String outFileName, String filter) throws IOException {
        try {
            FileReader reader = new FileReader(inFileName);
            FileWriter writer = new FileWriter(outFileName);
            try {
                Scanner scanner = new Scanner(reader);
                char[] chArr = scanner.nextLine().toCharArray();
                char[] chFilterArr = filter.toCharArray();
                System.out.println(Arrays.toString(chArr));
                System.out.println(Arrays.toString(chFilterArr));
                for (int i = 0; i < chArr.length; i++) {
                    for (int j = 0; j < chFilterArr.length; j++) {
                       if (chArr[i] == chFilterArr[j]) {
//                           char symbol = chArr[i];
//                           writer.write(symbol);
                           break;
                       }
                       char symbol = chArr[i];
                       writer.write(symbol);
                    }
                }
            } finally {
                reader.close();
                writer.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            filterFile("file1.txt", "charFilter.txt", "Яп");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
