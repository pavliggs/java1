package ru.progwards.java1.lessons.test;

public class Test1 {
    public static void main(String[] args) {
        System.out.println("Сделаю коммит, запушу в репо: робот, проверяй теперь всё это...");

        char char1 = 'В';
        System.out.println((int)char1);

        char[] chArr = new char[65536];
        chArr[1042] = 'В';
        chArr[1041] = '!';
        char char2 = chArr[(int)char1];
        System.out.println(char2);
        System.out.println(char1 + char2);
        System.out.println((char)('a' + 'a'));
    }
}
