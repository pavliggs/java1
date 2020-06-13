package ru.progwards.pavliggs.java2.N8dot1;

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
