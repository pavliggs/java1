package ru.progwards.java1.lessons.maps;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class UsageFrequency {
    String contentFile;

    public void processFile(String fileName) {
        try (FileReader reader = new FileReader(fileName)) {
            StringBuilder stringBuilder = new StringBuilder();
            // пока в файле есть символы - читаем их и записываем в stringBuilder
            while (reader.ready()) {
                stringBuilder.append((char)reader.read());
            }
            // в переменной contentFile будет находится содержимое файла
            contentFile = stringBuilder.toString();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // при помощи метода опеределяем сколько раз повторяется symbol в массиве символов charArr
    public static int numberOfRepetitionsSymbol(char[] charArr, char symbol) {
        int count = 0;
        for (int i = 0; i < charArr.length; i++) {
            if (symbol == charArr[i])
                count++;
        }
        return count;
    }

    // при помощи метода опеределяем сколько раз повторяется word в массиве слов stringArr
    public static int numberOfRepetitionsWord(String[] stringArr, String word) {
        int count = 0;
        for (int i = 0; i < stringArr.length; i++) {
            if (word.equals(stringArr[i]))
                count++;
        }
        return count;
    }

    // при помощи метода фильруем строку убирая все символы, кроме букв и цифр
    public static String getFilterString(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char ch : str.toCharArray()) {
            if (Character.isAlphabetic(ch) || Character.isDigit(ch))
                stringBuilder.append(ch);
        }
        return stringBuilder.toString();
    }

    public Map<Character, Integer> getLetters() {
        // создаем массив символов на основе фильтрованного значения переменной contentFile
        char[] arraySymbols = getFilterString(contentFile).toCharArray();

        // создаём пустой словарь
        Map<Character, Integer> map = new LinkedHashMap<>();
        /* проходимся по arraySymbols и добавляем в словарь элементы, в которых указывается символ и количество его
         * повторений в массиве arraySymbols */
        for (int i = 0; i < arraySymbols.length; i++) {
            map.putIfAbsent(arraySymbols[i], numberOfRepetitionsSymbol(arraySymbols, arraySymbols[i]));
        }
        return map;
    }

    public Map<String, Integer> getWords() {
        // преобразуем содержимое файла contentFile в массив слов, где разделителями являются любые символы, кроме букв и цифр
//        String[] arrayWords = contentFile.split("[^0-9a-zA-Zа-яА-Я]+");
        String[] arrayWords = contentFile.split("[\\W]+");

        // создаём пустой словарь
        Map<String, Integer> map = new LinkedHashMap<>();
        /* проходимся по arrayWords и добавляем в словарь элементы, в которых указывается слово и количество его
         * повторений в массиве arrayWords */
        for (int i = 1; i < arrayWords.length; i++) {
            map.putIfAbsent(arrayWords[i], numberOfRepetitionsWord(arrayWords, arrayWords[i]));
        }
        return map;
    }

    public static void main(String[] args) {
        UsageFrequency obj = new UsageFrequency();
        obj.processFile("wiki.test.tokens");
        System.out.println(obj.getLetters());
        System.out.println(obj.getWords());
    }
}
