package ru.progwards.pavliggs.N10dot2.T10dot2;

import java.io.IOException;

public class Test {
    public String test(String filename) throws IOException {
        try {
            filename.toString();
            return "File processing";
        } catch (NullPointerException e) {
            throw new IOException("File not found");
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println(new Test().test(null));
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
