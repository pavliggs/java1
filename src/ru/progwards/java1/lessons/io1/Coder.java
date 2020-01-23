package ru.progwards.java1.lessons.io1;

import java.io.*;
import java.util.Scanner;
import java.io.IOException;

public class Coder {
    public static void codeFile(String inFileName, String outFileName, char[] code, String logName) throws IOException {
        try {
            FileReader readFile = new FileReader(inFileName);
            FileWriter writeFile = new FileWriter(outFileName);
            try {
                Scanner scanner = new Scanner(readFile);
                char[] ch = scanner.nextLine().toCharArray();
                for (int i = 0; i < ch.length; i++) {
                    char symbol = code[(int)ch[i]];
                    writeFile.write(symbol);
                }
            } finally {
                readFile.close();
                writeFile.close();
            }
        } catch (IOException e) {
            PrintStream out = new PrintStream(new FileOutputStream(logName));
            System.setOut(out);
            System.out.println(e.getMessage());
        }
    }

//    public static void main(String[] args) {
//        try {
//            codeFile("file1.txt", "file3.txt", new char[65536], "logFileChar.txt");
//        } catch (IOException e) {
//            System.out.println(e);
//        }
//    }
}
