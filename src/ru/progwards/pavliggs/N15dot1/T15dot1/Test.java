package ru.progwards.pavliggs.N15dot1.T15dot1;

import java.util.HashMap;

public class Test {
    HashMap<Integer, String> a2map(int[] akey, String[] aval) {
        HashMap<Integer, String> hashMap = new HashMap<>();
        for (int i = 0; i < akey.length; i++) {
            hashMap.put(akey[i], aval[i]);
        }
        return hashMap;
    }
}
