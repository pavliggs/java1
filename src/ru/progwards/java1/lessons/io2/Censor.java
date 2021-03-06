package ru.progwards.java1.lessons.io2;

import java.io.RandomAccessFile;
import java.util.Scanner;

public class Censor {
    public static void censorFile(String inoutFileName, String[] obscene) throws CensorException {
        try(RandomAccessFile randomAccessFile = new RandomAccessFile(inoutFileName, "rw")) {
            //прочитаем строку и запишем её в переменную
            String strFile = randomAccessFile.readLine();
            Scanner scanner = new Scanner(strFile);
            //цикл будет продолжаться пока в строке есть слова
            while (scanner.hasNext()) {
                //при каждой итерации будем записывать слово в переменную word
                String word = scanner.next();
                //в цикле перебираем и сравниваем word со значением элемента в массиве obscene
                for (int i = 0; i < obscene.length; i++) {
                    /*если слово содержит значение элемента, то находим в strFile значение элемента массива
                    * и ставим курсор, а затем при помощи цикла заменяем символы на символ '*' (количество '*' равно
                    * количеству символов в obscene[i]) */
                    if (word.contains(obscene[i])) {
                        randomAccessFile.seek(strFile.indexOf(obscene[i]));
                        for (int j = 0; j < obscene[i].length(); j++) {
                            randomAccessFile.write(42);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new CensorException(inoutFileName, e.getMessage());
        }
    }

    public static class CensorException extends Exception {
        String fileName;
        String message;

        public CensorException(String fileName, String message) {
            this.message = message;
            this.fileName = fileName;
        }

        @Override
        public String toString() {
            return fileName + ":" + message;
        }
    }

    public static void main(String[] args) {
        try {
            censorFile("censor.txt", new String[]{"day", "storey", "write", "count", "two"});
        } catch (CensorException e) {
            System.out.println(e);
        }
    }
}
