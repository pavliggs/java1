package ru.progwards.pavliggs.N12dot1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NestedFigures {
    public List<Figure> list = new ArrayList<>();

    public Figure maxFigure() {
        return Collections.max(list);
    }

    public Figure minFigure() {
        return Collections.min(list);
    }

    //вложенные классы-фигуры
    public abstract static class Figure implements Comparable<Figure>{
        //при использовании интерфейса на основании данного метода в классе следует писать @Override
        @Override
        public int compareTo(Figure o) {
            return Double.compare(this.area(), o.area());
        }

        //периметр фигуры
        abstract double perimeter();
        //площадь фигуры
        double area() {
            return 0d;
        }

        public void printInfo() {
            System.out.println(this);
            System.out.println("периметр = " + perimeter());
            System.out.println("площадь = " + area());
            System.out.println();
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

    public static void main(String[] args) {
        NestedFigures figures = new NestedFigures();
        figures.list.add(new Segment(5));
        figures.list.add(new Square(7));
        figures.list.add(new Rectangle(4, 9));
        figures.list.add(new Rectangle(5, 12));
        figures.list.add(new Circle(10));
        figures.list.add(new Triangle(3, 6, 8));

        // при сортировке передаём null, т.к. у нас реализован интерфейс Comparable
        figures.list.sort(null);

        // если список не пустой, то удаляем первый и последний элементы списка
        if (!figures.list.isEmpty()) {
            figures.list.remove(0);
            figures.list.remove(figures.list.size() - 1);
        }

        for (Figure f: figures.list) {
            f.printInfo();
        }

    }
}

