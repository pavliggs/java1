package ru.progwards.pavliggs.java2.N1dot2;

public class HelloWordsWithParams {
    public static void main(String[] args) {
        if (args.length == 0)
            System.out.println("Нет параметров");
        for (int i = 0; i < args.length; i++) {
            System.out.println("Параметр " + i + " = " + args[i]);
        }
    }
}
