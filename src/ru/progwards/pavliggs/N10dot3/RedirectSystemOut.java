package ru.progwards.pavliggs.N10dot3;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class RedirectSystemOut {
    public static void main(String[] args) throws FileNotFoundException {
        PrintStream out = new PrintStream(new FileOutputStream("file_out.log"));

        System.setOut(out);

        System.out.println("Теперь вывод перенаправлен");
        System.out.println("в файл file_out.log");
    }
}
