package ru.progwards.pavliggs.N9dot1.N9dot3dotTest;

import java.math.BigDecimal;

public class Rectangle {
    Rectangle(BigDecimal a, BigDecimal b) {
        this.a = a;
        this.b = b;
    }
    public BigDecimal a;
    public BigDecimal b;

    static boolean rectCompare(Rectangle r1, Rectangle r2) {
        if(r1.a.multiply(r1.b).compareTo(r2.a.multiply(r2.b)) == 0)
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        Rectangle rect1 = new Rectangle(new BigDecimal(3), new BigDecimal(2));
        Rectangle rect2 = new Rectangle(new BigDecimal(2), new BigDecimal(3));

        System.out.println(rectCompare(rect1, rect2));
    }
}

