package ru.progwards.java1.lessons.register1;

public class Bit {
    private boolean value;

    public Bit() {
       value = false;
    }

    public Bit(boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value ? "1" : "0";
    }

    public static void main(String[] args) {
        System.out.println(new Bit());
    }
}
