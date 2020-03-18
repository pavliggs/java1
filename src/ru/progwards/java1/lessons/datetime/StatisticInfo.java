package ru.progwards.java1.lessons.datetime;

public class StatisticInfo {
    public String sectionName;
    public int fullTime;
    public int selfTime;
    int count;

    StatisticInfo(String sectionName) {
        this.sectionName = sectionName;
    }

    @Override
    public String toString() {
        return "StatisticInfo{" +
                "sectionName='" + sectionName + '\'' +
                ", fullTime=" + fullTime +
                ", selfTime=" + selfTime +
                ", count=" + count +
                '}';
    }
}
