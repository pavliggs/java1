package ru.progwards.java1.lessons.io1;

import java.io.*;
import java.util.Arrays;
//import java.util.Scanner;

public class Coder {
    public static void codeFile(String inFileName, String outFileName, char[] code, String logName) {
        try {
            FileReader readFile = new FileReader(inFileName);
            FileWriter writeFile = new FileWriter(outFileName);
            try {
                //итерации будут производиться со смещением символа пока в файле есть символы
                while (readFile.ready()) {
                    writeFile.write(code[readFile.read()]);
                }
            } finally {
                readFile.close();
                writeFile.close();
            }
        } catch (IOException e) {
            try {
                PrintStream out = new PrintStream(new FileOutputStream(logName));
                System.setOut(out);
                System.out.println(e.getMessage());
            } catch (IOException e1) {
                System.out.println(e1);
            }
        }
    }

    public static void main(String[] args) {
        char[] charArr = new char[65536];
        Arrays.fill(charArr, 'H');
        codeFile("file1.txt", "file3.txt", charArr, "logFileChar.txt");
    }
}
