package ru.progwards.java1.lessons.io2;

import java.io.RandomAccessFile;
import java.util.Scanner;

public class Censor {
    public static void censorFile(String inoutFileName, String[] obscene) {
        try(RandomAccessFile randomAccessFile = new RandomAccessFile(inoutFileName, "rw")) {
            String strFile = randomAccessFile.readLine();
            Scanner scanner = new Scanner(strFile);
            while (scanner.hasNext()) {
                String word = scanner.next();
                for (int i = 0; i <= obscene.length; i++) {
                    if (getFilterWordAlphabet(word).equals(obscene[i])) {
                        randomAccessFile.seek(strFile.indexOf(word));
                        for (int j = 0; j < getFilterWordAlphabet(word).length(); j++) {
                            randomAccessFile.write(42);
                        }
                    }
                }
            }
            randomAccessFile.seek(0);
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
        public String getMessage() {
            return super.getMessage();
        }

        @Override
        public String toString() {
            return fileName + ":" + getMessage();
        }
    }

    public static void main(String[] args) {
//        try {
//            censorFile("censor.txt", new String[]{"two", "count", "write", "storey", "day"});
//        } catch (Exception e) {
//            System.out.println(e);
//        }

        censorFile("censor.txt", new String[]{"two", "count", "write", "storey", "day"});
    }
}
