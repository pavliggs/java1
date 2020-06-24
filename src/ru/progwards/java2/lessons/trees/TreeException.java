package ru.progwards.java2.lessons.trees;

public class TreeException extends Exception {
    private String msg;

    public TreeException(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}
