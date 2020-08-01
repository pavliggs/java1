package ru.progwards.pavliggs.java2.N10dot3.tasks;

import ru.progwards.pavliggs.java2.N10dot3.Task;

public class Dispersion implements Task {
    private long modifiedTime;

    @Override
    public long getModifiedTime() {
        return modifiedTime;
    }

    @Override
    public void setModifiedTime(long time) {
        modifiedTime = time;
    }

    @Override
    public String process(byte[] data) {
        if (data.length == 0)
            return "Нет данных";

        long sum = 0;
        for (int i = 0; i < data.length; i++) {
            sum += data[i];
        }
        double mathExpectation = (double)sum / data.length;

        sum = 0;
        for (int i = 0; i < data.length; i++) {
            double diff = mathExpectation - data[i];
            sum += diff * diff;
        }
        double dispersion = (double)sum / data.length;

        return "Дисперсия: " + dispersion + ", среднеквадратичное отклонение: " + Math.sqrt(dispersion);
    }
}
