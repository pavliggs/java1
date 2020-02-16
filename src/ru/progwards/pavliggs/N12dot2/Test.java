package ru.progwards.pavliggs.N12dot2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Test {
    public List<Integer> filter(List<Integer> list) {
        int result = 0;
        for (Integer i: list) {
            result += i;
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) >= (result / 100))
                list.remove(i);
        }
        return list;
    }

    public static void main(String[] args) {
        Test test = new Test();
        List<Integer> list = new LinkedList<>();
        for (int i = -4; i < 46; i++) {
            list.add(i);
        }
        System.out.println(list);
        System.out.println(new Test().filter(list));
    }
}
