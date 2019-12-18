package ru.progwards.pavliggs;

public class Point2D {
    private int x;
    private int y;

    Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return x + "," + y;
    }
}
