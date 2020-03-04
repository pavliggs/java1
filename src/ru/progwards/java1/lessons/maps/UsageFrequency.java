package ru.progwards.java1.lessons.maps;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class UsageFrequency {
    private String contentFile;

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

    public Map<Character, Integer> getLetters() {
        // создаем массив символов на основе фильтрованного значения переменной contentFile
        char[] arraySymbols = getFilterString(contentFile).toCharArray();

        // создаём пустой словарь
        Map<Character, Integer> treeMap = new HashMap<>();
        for (int i = 0; i < arraySymbols.length; i++) {
            // если элемент с таким ключом уже есть, то увеличиваем его значение на 1 и перезаписываем
            if (treeMap.containsKey(arraySymbols[i])) {
                int quantity = treeMap.get(arraySymbols[i]) + 1;
                treeMap.put(arraySymbols[i], quantity);
            }
            // если элемента с таким ключом нет в treeMap, то добавляем его и ставим значение 1
            treeMap.putIfAbsent(arraySymbols[i], 1);
        }
        return treeMap;
    }

    public Map<String, Integer> getWords() {
        // преобразуем содержимое файла contentFile в массив слов, где разделителями являются любые символы, кроме букв и цифр
        String[] arrayWords = contentFile.split("[^0-9a-zA-Zа-яА-Я]+");

        // создаём пустой словарь
        Map<String, Integer> treeMap = new HashMap<>();
        for (int i = 1; i < arrayWords.length; i++) {
            if (treeMap.containsKey(arrayWords[i])) {
                int quantity = treeMap.get(arrayWords[i]) + 1;
                treeMap.put(arrayWords[i], quantity);
            }
            treeMap.putIfAbsent(arrayWords[i], 1);
        }
        return treeMap;
    }

    // при помощи метода фильруем строку убирая все символы, кроме букв и цифр
    private String getFilterString(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char ch : str.toCharArray()) {
            if (Character.isAlphabetic(ch) || Character.isDigit(ch))
                stringBuilder.append(ch);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        UsageFrequency obj = new UsageFrequency();
        obj.processFile("wiki.train.tokens");

        long start = System.currentTimeMillis();
        System.out.println(obj.getLetters());
        long finish1 = System.currentTimeMillis() - start;

        start = System.currentTimeMillis();
        System.out.println(obj.getWords());
        long finish2 = System.currentTimeMillis() - start;

        System.out.println("Время выполнения метода getLetters() = " + finish1);
        System.out.println("Время выполнения метода getWords() = " + finish2);
    }
}
