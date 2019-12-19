package ru.progwards.pavliggs.inheritanceExample;

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
        Rectangle rectangle = new Rectangle(4, 9);
        Circle circle = new Circle(10);
        Triangle triangle = new Triangle(3, 6, 8);

        printInfo(segment);
        printInfo(square);
        printInfo(rectangle);
        printInfo(circle);
        printInfo(triangle);
    }
}
