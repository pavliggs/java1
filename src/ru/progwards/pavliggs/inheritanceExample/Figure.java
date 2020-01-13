package ru.progwards.pavliggs.inheritanceExample;

public class Figure implements Comparable<Figure>, AreaComparable {
    //при использовании интерфейса на основании данного метода в классе следует писать @Override
    @Override
    public int compareTo(Figure o) {
        return Double.compare(this.area(), o.area());
    }

    @Override
    public int compareArea(Figure figure) {
        return Double.compare(this.area(), figure.area());
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
