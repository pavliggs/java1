package ru.progwards.java1.lessons.interfaces;

public interface CompareWeight {
    CompareResult compareWeight(CompareWeight smthHasWeigt);

    enum CompareResult {
        LESS,
        EQUAL,
        GREATER,
    }
}