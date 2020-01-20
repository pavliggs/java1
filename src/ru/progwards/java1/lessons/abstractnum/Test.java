package ru.progwards.java1.lessons.abstractnum;

public class Test {
    public static void main(String[] args) {
        Figure3D fig1 = new Cube(new IntNumber(3));
        Figure3D fig2 = new Pyramid(new IntNumber(3));
        Figure3D fig3 = new Pyramid(new DoubleNumber(3));
        Figure3D fig4 = new Cube(new DoubleNumber(3));
        System.out.println(fig1.volume());
        System.out.println(fig2.volume());
        System.out.println(fig3.volume());
        System.out.println(fig4.volume());
        System.out.println(new DoubleNumber(3).newNumber("3.14"));
        System.out.println(new Pyramid(new DoubleNumber(80.09388573060035)).volume());
    }
}
