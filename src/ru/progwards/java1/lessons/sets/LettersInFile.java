package ru.progwards.java1.lessons.sets;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LettersInFile {
    public static String process(String fileName) throws IOException {
        try(FileReader fileReader = new FileReader(fileName)) {
            Scanner scanner = new Scanner(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            // проходимся по словам во всех строках файла fileName
            while (scanner.hasNextLine()) {
                // присваиваем str слово в файле
                String str = scanner.next();
                // фильтруем слово через цикл так, чтобы остались только символы алфавита и запишем их в stringBuilder
                for (char ch : str.toCharArray()) {
                    if (Character.isAlphabetic(ch))
                        stringBuilder.append(ch);
                }
            }
            // создадим пустое множество
            TreeSet<Character> charSet = new TreeSet<>();
            /* заполним множество символами, содержащимися в stringBuilder
            * все символы во множестве будут уникальны и отсортируются в соответствие с компаратором,
            * который реализует класс Character */
            for (Character ch : stringBuilder.toString().toCharArray())
                charSet.add(ch);
            Iterator<Character> iterator = charSet.iterator();
            StringBuilder result = new StringBuilder();
            // при помощи итератора пройдёмся по элементам множества и каждый элемент добавим в result
            while (iterator.hasNext()) {
                Character ch = iterator.next();
                result.append(ch);
            }
            return result.toString();
        } catch (IOException e) {
            throw e;
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println(process("letter.txt"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
