package ru.progwards.pavliggs.N15dot2;

import java.util.HashMap;
import java.util.Map;

public class Test {
    int fillHoles(Map<Integer, String> map, int size) {
        int count = 0;
        for (int i = 1; i <= size; i++) {
            String value = map.putIfAbsent(i, "Число " + i);
            if (value == null) {
                map.put(i, "Число " + i);
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10; i += 2) {
            map.put(i, "Число " + i);
        }

        System.out.println(map);

        System.out.println(new Test().fillHoles(map, 10));
    }
}
