package ru.progwards.pavliggs.N10dot3;

import java.io.FileWriter;
import java.io.IOException;

public class LogFile {
    public static boolean log(String msg) {
        return log(msg, true);
    }

    public static boolean log(String msg, boolean toConsole) {
        if (toConsole)
            System.out.println(msg);

        try {
            FileWriter writer = new FileWriter("logfile.txt", true);
            try {
                writer.write(msg + "\n");
            } finally {
                writer.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 55; i++) {
            log("Всё хорошо, процесс идёт по плану");
            log("Потоки символов сейчас зальют экраны");
            log("И всё это запишется в лог-файл?");
            log("Это итерация " + i);
        }
    }
}
