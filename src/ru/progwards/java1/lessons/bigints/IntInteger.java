package ru.progwards.java1.lessons.bigints;

public class IntInteger extends AbsInteger {
    int a;

    IntInteger(int a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return Integer.toString(a);
    }

    public static void main(String[] args) {
        IntInteger a = new IntInteger(100);
        IntInteger b = new IntInteger(99);

        System.out.println(a.a + b.a);
    }
}
