package ru.progwards.pavliggs.N18dot2;

import java.util.StringTokenizer;

public class Test {
    String swapWords(String sentance) {
        StringBuilder stringBuilder = new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer(sentance, " .,-!\n");
        while (tokenizer.hasMoreElements()) {
            String prevWord = tokenizer.nextElement().toString();
            if (tokenizer.countTokens() % 2 == 0 && tokenizer.countTokens() != 0)
                stringBuilder.append(tokenizer.nextElement() + " " + prevWord + " ");
            else if (tokenizer.countTokens() % 2 == 1)
                stringBuilder.append(tokenizer.nextElement() + " " + prevWord + " ");
            else
                stringBuilder.append(prevWord + " ");
        }
        return stringBuilder.toString().trim();
    }

    public static void main(String[] args) {
        String str = "Убитых словом, добивают молчанием. (c) Уильям Шекспир.";
        System.out.println(new Test().swapWords(str));
        System.out.println(new Test().swapWords("Слово - серебро, молчание - золото!"));
    }
}
