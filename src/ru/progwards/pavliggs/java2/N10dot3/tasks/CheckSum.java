package ru.progwards.pavliggs.java2.N10dot3.tasks;

import ru.progwards.pavliggs.java2.N10dot3.Task;

public class CheckSum implements Task {
    @Override
    public String process(byte[] data) {
        if (data.length == 0)
            return "Нет данных";

        byte checkSum = 0;
        for (int i = 0; i < data.length; i++) {
            checkSum += data[i];
        }

        return "Контрольная сумма: " + checkSum;
    }
}
