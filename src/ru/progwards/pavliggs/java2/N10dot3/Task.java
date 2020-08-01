package ru.progwards.pavliggs.java2.N10dot3;

public interface Task {
    // методы для получения и установки времени создания файла
    public long getModifiedTime();
    public void setModifiedTime(long time);


    public String process(byte[] data);
}
