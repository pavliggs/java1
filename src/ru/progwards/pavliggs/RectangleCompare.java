package ru.progwards.pavliggs;

public class RectangleCompare {
    private double a;
    private double b;

    public RectangleCompare(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double area() {
        return a * b;
    }

    public int compareTo(RectangleCompare anRectangle) {
        if (this.area() > anRectangle.area())
            return 1;
        else if (this.area() == anRectangle.area())
            return 0;
        else
            return -1;
    }

    public static void main(String[] args) {
        RectangleCompare rectangle1 = new RectangleCompare(2, 5);
        RectangleCompare rectangle2 = new RectangleCompare(2, 3);

        System.out.println(rectangle1.compareTo(rectangle2));
    }

}
