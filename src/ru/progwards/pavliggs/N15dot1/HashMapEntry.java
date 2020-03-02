package ru.progwards.pavliggs.N15dot1;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashMapEntry {
    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("ivanov", "Иванов Иван");
        hashMap.put("petrov", "Петров Леонид");
        hashMap.put("kontanon", "Контин Николай");
        hashMap.put("student", "Романов Алексей");
        hashMap.put("haumov", "Наумов Павел");

        System.out.println(hashMap);

        System.out.println("Через forEach");
        for (Map.Entry<String, String> entry : hashMap.entrySet())
            System.out.println(entry);

        System.out.println("\nЧерез итератор");
        Iterator<Map.Entry<String, String>> iterator = hashMap.entrySet().iterator();

        while (iterator.hasNext())
            System.out.println(iterator.next());
    }
}
