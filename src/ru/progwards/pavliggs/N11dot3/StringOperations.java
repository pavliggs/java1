package ru.progwards.pavliggs.N11dot3;

public class StringOperations {
    public static void main(String[] args) {
        String regNum = "     K699tO  29   ";

        System.out.println(regNum.toLowerCase());
        System.out.println(regNum.toUpperCase());
        System.out.println(regNum.replace(" ", ""));
        System.out.println(regNum.trim());
        System.out.println(regNum.substring(7));
        System.out.println(regNum.substring(7, 10));
        System.out.println(regNum.indexOf("9"));
    }
}
