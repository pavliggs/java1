package ru.progwards.pavliggs;

public class ObjectsCompare {
    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = new Object();

        String str1 = "12345";
        String str2 = "12345";

        Integer a = 4;
        Integer b = 7;

        System.out.println(a.compareTo(b));

        System.out.println(str1 == str2);
        System.out.println(str1.equals(str2));

        System.out.println(obj1 == obj2);
        System.out.println(obj1.equals(obj2));
    }
}
