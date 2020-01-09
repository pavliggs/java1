package ru.progwards.pavliggs;

import java.util.Arrays;
/*
этот статический import позволяет не писать длинные наименования при создании экземпляров класса,
т.к. все внутренние классы внешнего класса NestedFigures будут импортированы в данный класс
*/
import static ru.progwards.pavliggs.NestedFigures.*;

public class DemoNestedFromOtherClass {
    public static void main(String[] args) {
        Segment segment = new Segment(10);
        Square square = new Square(16);
        Rectangle rectangle1 = new Rectangle(2, 7);
        Rectangle rectangle2 = new Rectangle(4, 8);
        Circle circle = new Circle(5);
        Triangle triangle = new Triangle(3, 4, 5);

        Figure[] figures = {segment, square, rectangle1, rectangle2, circle, triangle};

        //метод sort стал доступен для массива с объектами только после использования интерфейса Comparable<Figure>
        Arrays.sort(figures);

        for (Figure f : figures) {
            printInfo(f);
        }

        System.out.println("segment.compareTo(square) = " + segment.compareTo(square));
    }
}
