package ru.progwards.pavliggs.N11dot2;

import java.util.Scanner;

public class ScannerString {
    public static void main(String[] args) {
        String str = "Городу Котласу в этом году исполняется 103 года";

        try(Scanner scanner = new Scanner(str)) {
            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    int num = scanner.nextInt();
                    System.out.println("Число: " + num);
                }
                else {
                    String word = scanner.next();
                    System.out.println("Слово: " + word);
                }
            }
        }

        //строка написана небрежно
        String str2 = "1 ,   2, 3   ,4, 5,     6, 7     , 8  ,     9       ,     0";
        //при помощи useDelimiter("\\s*,\\s*") устанавливаем разделитель для слов (по умолчанию - пробел)
        try(Scanner scanner = new Scanner(str2).useDelimiter("\\s*,\\s*")) {
            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    int num = scanner.nextInt();
                    System.out.println("Число: " + num);
                }
            }
        }
    }
}
