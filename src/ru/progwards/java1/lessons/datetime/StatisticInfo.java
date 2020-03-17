package ru.progwards.java1.lessons.datetime;

public class StatisticInfo {
    private String sectionName;
    private long fullTime;
    public long selfTime;
    private int count;

    StatisticInfo(String sectionName, long fullTime, long selfTime, int count) {
        this.sectionName = sectionName;
        this.fullTime = fullTime;
        this.selfTime = selfTime;
        this.count = count;
    }

    long getFullTime() {
        return fullTime;
    }

    long getSelfTime() {
        return selfTime;
    }

    int getCount() {
        return count;
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
