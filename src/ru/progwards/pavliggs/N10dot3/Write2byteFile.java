package ru.progwards.pavliggs.N10dot3;

import java.io.FileOutputStream;
import java.io.IOException;

public class Write2byteFile {
    public static void main(String[] args) {
        try {
            FileOutputStream byteFile = new FileOutputStream("file2.bin");
            try {
                for (byte b = 0; b < 127; b++) {
                    byteFile.write(b);
                }
            } finally {
                byteFile.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
