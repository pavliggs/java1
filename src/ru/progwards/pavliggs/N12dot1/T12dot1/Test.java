package ru.progwards.pavliggs.N12dot1.T12dot1;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test {
    public List<Integer> listAction(List<Integer> list) {
        list.remove(Collections.min(list));
        list.add(0, list.size());
        list.add(2, Collections.max(list));
        return list;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 20; i += 2) {
            list.add(i);
        }

        System.out.println(list);

        System.out.println(new Test().listAction(list));

    }
}
