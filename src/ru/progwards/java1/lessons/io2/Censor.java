package ru.progwards.java1.lessons.io2;

import java.io.RandomAccessFile;
import java.util.Scanner;

public class Censor {
    public static void censorFile(String inoutFileName, String[] obscene) {
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
                    /*если слово равно значению элемента, то ставим курсор на начало слова word, а затем
                    * при помощи цикла заменяем символы в этом слове на символ '*'  */
                    if (getFilterWordAlphabet(word).equals(obscene[i])) {
                        randomAccessFile.seek(strFile.indexOf(word));
                        for (int j = 0; j < getFilterWordAlphabet(word).length(); j++) {
                            randomAccessFile.write(42);
                        }
                    }
                }
            }
        } catch (Exception e) {
            try {
                throw new CensorException(inoutFileName);
            } catch (CensorException e1) {
                System.out.println(e1);
            }
        }
    }

    public static String getFilterWordAlphabet(String word) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char ch: word.toCharArray()) {
            if (Character.isAlphabetic(ch) || Character.isDigit(ch))
                stringBuilder.append(ch);
        }
        return stringBuilder.toString();
    }

    public static class CensorException extends Exception {
        String fileName;
        public CensorException(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public String toString() {
            return fileName + ":" + getMessage();
        }
    }

    public static void main(String[] args) {
        censorFile("censor.txt", new String[]{"two", "count", "write", "storey", "day"});
    }
}
