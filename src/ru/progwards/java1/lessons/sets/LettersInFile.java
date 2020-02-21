package ru.progwards.java1.lessons.sets;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LettersInFile {
    public static String process(String fileName) throws IOException {
        try(FileReader fileReader = new FileReader(fileName)) {
            Scanner scanner = new Scanner(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            while (scanner.hasNextLine()) {
                String str = scanner.next();
                for (char ch : str.toCharArray()) {
                    if (Character.isAlphabetic(ch))
                        stringBuilder.append(ch + " ");
                }
            }
            String[] letter = stringBuilder.toString().trim().split(" ");
            List<String> listLetter = Arrays.asList(letter);
            Set<String> stringSet = new TreeSet<>(listLetter);
            Iterator<String> iterator = stringSet.iterator();
            StringBuilder stringBuilder1 = new StringBuilder();
            while (iterator.hasNext()) {
                String str = iterator.next();
                stringBuilder1.append(str);
            }
            return stringBuilder1.toString();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println(process("letter.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
