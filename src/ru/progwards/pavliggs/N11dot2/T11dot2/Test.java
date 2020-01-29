package ru.progwards.pavliggs.N11dot2.T11dot2;

import java.util.Scanner;

public class Test {
    public void scanLines() {
        try (Scanner scanner = new Scanner(System.in)) {
            //в ожидании ввода строки; затем полученную строку кладём в переменную str
            String str = scanner.nextLine();
            //цикл будет работать, пока содержимое строки не станет "/stop"
            while (!str.contains("/stop")) {
                //если строка содержит "Привет", то выводим на консоль "Здравствуйте!" и ждем следующую строку,
                //содержимое которой перезапишет содержимое переменной str
                if (str.contains("Привет")) {
                    System.out.println("Здравствуйте!");
                    str = scanner.nextLine();
                }
                else if (str.contains("как дела")) {
                    System.out.println("Хорошо");
                    str = scanner.nextLine();
                }
                else {
                    System.out.println(str);
                    str = scanner.nextLine();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Test().scanLines();
    }
}
