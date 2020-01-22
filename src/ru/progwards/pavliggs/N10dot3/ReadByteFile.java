package ru.progwards.pavliggs.N10dot3;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class ReadByteFile {
    public static void main(String[] args) {
        try {
            FileInputStream byteFile = new FileInputStream("file2.bin");
            try {
                byte[] byteArr = byteFile.readAllBytes();
                System.out.println(Arrays.toString(byteArr));
            } finally {
                byteFile.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
