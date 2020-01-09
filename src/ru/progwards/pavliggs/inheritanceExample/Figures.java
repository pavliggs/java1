package ru.progwards.pavliggs.inheritanceExample;

import java.util.Arrays;

public class Figures {
    public static void printInfo(Figure figure) {
        System.out.println(figure);
        System.out.println("периметр = " + figure.perimeter());
        System.out.println("площадь = " + figure.area());
        System.out.println("");
    }

    public static void main(String[] args) {
        Segment segment = new Segment(5);
        Square square = new Square(7);
        Rectangle rectangle1 = new Rectangle(4, 9);
        Rectangle rectangle2 = new Rectangle(5, 12);
        Circle circle = new Circle(10);
        Triangle triangle = new Triangle(3, 6, 8);

        Figure[] figures = {segment, square, rectangle1, rectangle2, circle, triangle};

        //метод sort стал доступен для массива с объектами только после использования интерфейса Comparable<Figure>
        Arrays.sort(figures);

        for (Figure f : figures) {
            printInfo(f);
        }

        System.out.println("segment.compareTo(square) = " + segment.compareTo(square));
        System.out.println("segment.compareArea(square) = " + segment.compareArea(square));

//        System.out.println(rectangle1.equals(rectangle2));
    }
}
