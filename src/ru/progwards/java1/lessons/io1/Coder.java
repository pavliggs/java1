package ru.progwards.java1.lessons.io1;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Coder {
    public static void codeFile(String inFileName, String outFileName, char[] code, String logName) {
        try {
            FileReader readFile = new FileReader(inFileName);
            FileWriter writeFile = new FileWriter(outFileName);
            try {
                Scanner scanner = new Scanner(readFile);
                //создадим цикл для того, чтобы можно было прочитать все строки в файле inFileName
                while (scanner.hasNextLine()) {
                    //приводим строку в файле inFileName к массиву из символов
                    char[] chArr = scanner.nextLine().toCharArray();
                    for (int i = 0; i < chArr.length; i++) {
                        /*присваиваем переменной типа char значение элемента массива code с индексом равным целочисленному
                         * представлению элемента массива chArr и записываем значение переменной в файл outFileName*/
                        char symbol = code[(int)chArr[i]];
                        writeFile.write(symbol);
                    }
                    /*чтобы в файл outFileName всё записывалось также построчно как и в файле inFileName в конце
                    * каждой итерации цикла while будем переводить строку на следующую, кроме последней строки*/
                    if (scanner.hasNextLine())
                        writeFile.write('\n');
                }
            } finally {
                readFile.close();
                writeFile.close();
            }
        } catch (IOException e) {
            try {
                PrintStream out = new PrintStream(new FileOutputStream(logName));
                System.setOut(out);
            } catch (IOException e1) {
                System.out.println(e.getMessage());
            }

        }
    }

    public static void main(String[] args) {
        char[] charArr = new char[65536];
        Arrays.fill(charArr, 'H');
        codeFile("file1.txt", "file3.txt", charArr, "logFileChar.txt");
    }
}
