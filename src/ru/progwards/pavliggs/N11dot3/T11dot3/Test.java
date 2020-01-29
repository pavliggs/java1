package ru.progwards.pavliggs.N11dot3.T11dot3;

import java.util.Arrays;
import java.util.Scanner;

public class Test {
    public String invertWords(String sentence) {
        //вариант 1
//        String[] strArr = sentence.split(" ");
//        String[] strArr1 = new String[strArr.length];
//        String str = "";
//        for (int i = 0; i < strArr.length; i++) {
//            strArr1[i] = strArr[strArr.length - (i + 1)];
//            if (i == strArr.length - 1)
//                str += strArr1[i];
//            else
//                str += strArr1[i] + ".";
//        }
//        return str;

        //вариант 2
        String strReverse = "";
        try (Scanner scanner = new Scanner(sentence)) {
            while (scanner.hasNext()) {
                String word = scanner.next();
                if (scanner.hasNext())
                    strReverse = "." + word + strReverse;
                else
                    strReverse = word + strReverse;
            }
        }
        return strReverse;
    }

    public static void main(String[] args) {
        System.out.println(new Test().invertWords("Буря мглою небо кроет"));
    }
}
