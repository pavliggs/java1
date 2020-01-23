package ru.progwards.java1.lessons.io1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CharFilter {
    public static void filterFile(String inFileName, String outFileName, String filter) {
        try {
            FileReader reader = new FileReader(inFileName);
            FileWriter writer = new FileWriter(outFileName);
            try {
                Scanner scanner = new Scanner(reader);
                //приводим строку в файле inFileName к массиву из символов
                char[] chArr = scanner.nextLine().toCharArray();
                //приводим фильтрующую строку к массиву из символов
                char[] chFilterArr = filter.toCharArray();
                //при помощи двух вложенных циклов сравниваем элементы массивов между собой
                for (int i = 0; i < chArr.length; i++) {
                    for (int j = 0; j < chFilterArr.length; j++) {
                        /*если элементы массивов равны, то выходим из внутреннего цикла и начинаем новую итерацию
                        * внешнего цикла*/
                       if (chArr[i] == chFilterArr[j])
                           break;
                       /*если итерация внутреннего цикла является последней, то присвоим эелементу типа char значение
                       * элемента из массива chArr файла inFileName и запишем этот элемент в файл outFileName*/
                       if (j == chFilterArr.length - 1) {
                           char symbol = chArr[i];
                           writer.write(symbol);
                       }
                    }
                }
            } finally {
                reader.close();
                writer.close();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        filterFile("file1.txt", "charFilter.txt", "ое");
    }
}
