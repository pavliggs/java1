package ru.progwards.pavliggs;

import java.util.Arrays;

public class NestedFigures {
    //вложенные классы-фигуры
    public abstract static class Figure implements Comparable<Figure>{
        //при использовании интерфейса на основании данного метода в классе следует писать @Override
        @Override
        public int compareTo(Figure o) {
            return Double.compare(this.area(), o.area());
        }

        //периметр фигуры
        double perimeter() {
            return 0d;
        }
        //площадь фигуры
        double area() {
            return 0d;
        }

        @Override
        public String toString() {
            return "Абстрактная фигура";
        }
    }

    public static class Segment extends Figure {
        double a;

        Segment(double a) {
            this.a = a;
        }

        //переопределение метода родителя
        @Override
        double perimeter() {
            return a;
        }

        @Override
        public String toString() {
            return "Отрезок длиной " + a;
        }
    }

    public static class Circle extends Figure{
        double radius;

        Circle(double radius) {
            this.radius = radius;
        }

        @Override
        double perimeter() {
            return 2 * Math.PI * radius;
        }

        @Override
        double area() {
            return Math.PI * radius * radius;
        }

        @Override
        public String toString() {
            return "Окружность с радиусом " + radius;
        }
    }

    public static class Square extends Segment {
        /*
        необходимо при помощи super вызвать конструктор родителя ЯВНО
        переменную "a" возьмем от родителя
        */
        Square(double a) {
            super(a);
        }

        @Override
        double perimeter() {
            return 4 * a;
        }

        @Override
        double area() {
            return a * a;
        }

        @Override
        public String toString() {
            return "Квадрат со стороной " + a;
        }
    }

    public static class Rectangle extends Square {
        double b;

        Rectangle(double a, double b) {
            super(a);
            this.b = b;
        }

        @Override
        double perimeter() {
            return 2 * (a + b);
        }

        @Override
        double area() {
            return a * b;
        }

        @Override
        public String toString() {
            return "Прямоугольник со сторонами " + a + " и " + b;
        }

        @Override
        public boolean equals(Object anObject) {
            if (this == anObject) return true;
            if (anObject == null || getClass() != anObject.getClass()) return false;
            Rectangle rectangle = (Rectangle) anObject;
            return Double.compare(area(), rectangle.area()) == 0;
        }
    }

    public static class Triangle extends Segment{
        double b, c;

        Triangle(double a, double b, double c) {
            super(a);
            this.b = b;
            this.c = c;
        }

        @Override
        double perimeter() {
            return a + b + c;
        }

        @Override
        double area() {
            //вычисляем площадь по формуле Герона
            double hP = perimeter() / 2;
            return Math.sqrt(hP * (hP - a) * (hP - b) * (hP - c));
        }

        @Override
        public String toString() {
            return "Треугольник со сторонами " + a + ", " + b + " и " + c;
        }
    }

    public static void printInfo(Figure figure) {
        System.out.println(figure);
        System.out.println("периметр = " + figure.perimeter());
        System.out.println("площадь = " + figure.area());
        System.out.println("");
    }

//    public static void main(String[] args) {
//        Segment segment = new Segment(5);
//        Square square = new Square(7);
//        Rectangle rectangle1 = new Rectangle(4, 9);
//        Rectangle rectangle2 = new Rectangle(5, 12);
//        Circle circle = new Circle(10);
//        Triangle triangle = new Triangle(3, 6, 8);
//
//        Figure[] figures = {segment, square, rectangle1, rectangle2, circle, triangle};
//
//        //метод sort стал доступен для массива с объектами только после использования интерфейса Comparable<Figure>
//        Arrays.sort(figures);
//
//        for (Figure f : figures) {
//            printInfo(f);
//        }
//
//        System.out.println("segment.compareTo(square) = " + segment.compareTo(square));
//
////        System.out.println(rectangle1.equals(rectangle2));
//    }
}
