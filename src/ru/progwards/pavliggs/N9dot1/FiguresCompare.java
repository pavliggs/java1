package ru.progwards.pavliggs.N9dot1;

//наследование применено для доступа к свойствам, методам и классам класса FiguresCompare
public class FiguresCompare extends NestedFigures {

    //пропишем вложенный класс
    static class CompareCr {
        boolean greater(Figure figure1, Figure figure2) {
            return figure1.perimeter() > figure2.perimeter();
        }
    }

    //свойства
    private Figure[] figures;
    public CompareCr figuresComparator = new CompareCr();

    //конструктор 1
    FiguresCompare(Figure[] figures) {
        this.figures = figures;
    }

    //конструктор 2
    FiguresCompare(Figure[] figures, CompareCr figuresComparator) {
        this(figures);
        this.figuresComparator = figuresComparator;
    }

    Figure getMaxFigure() {
        Figure result = null;
        for (Figure figure: figures) {
            if (result == null || figuresComparator.greater(figure, result))
                result = figure;
        }
        return result;
    }

    public static void main(String[] args) {
        Segment segment = new Segment(5);
        Square square = new Square(7);
        Rectangle rectangle1 = new Rectangle(4, 9);
        Rectangle rectangle2 = new Rectangle(5, 12);
        Circle circle = new Circle(5);
        Triangle triangle = new Triangle(3, 6, 8);

        Figure[] figures = {segment, square, rectangle1, rectangle2, circle, triangle};

        for (Figure f : figures) {
            System.out.println(f);
        }
        System.out.println();

        FiguresCompare fc = new FiguresCompare(figures);
        System.out.println("Фигура с максимальным периметром:");
        printInfo(fc.getMaxFigure());


        //1 вариант (создание локального класса)
        //добавим локальный класс с переопределенным методом сравнивающим площади
        class FiguresComparatorArea extends CompareCr {
            @Override
            boolean greater(Figure figure1, Figure figure2) {
                return figure1.area() > figure2.area();
            }
        }

        FiguresComparatorArea fca = new FiguresComparatorArea();

        //fca - передается как параметр, а это значит, что мы передаем функцию greater как параметр
//        FiguresCompare fcArea = new FiguresCompare(figures, fca);

        //2 вариант (использование анонимного класса)
        //здесь вторым параметром передаем анонимный класс, что будет более правильно, чем 1 вариант
        FiguresCompare fcArea =
                new FiguresCompare(
                        figures,
                        new CompareCr(){
                            @Override
                            boolean greater(Figure figure1, Figure figure2) {
                                return figure1.area() > figure2.area();
                            }
                        });

        System.out.println("Фигура с максимальной площадью:");
        printInfo(fcArea.getMaxFigure());
    }
}
