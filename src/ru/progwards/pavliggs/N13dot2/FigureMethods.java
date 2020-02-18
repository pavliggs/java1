package ru.progwards.pavliggs.N13dot2;

public class FigureMethods {
    String figDetect(Figure fig) {
        try {
            if (fig.getClass() == Square.class) {
                Square square = (Square)fig;
                return "Сторона квадрата " + square.getSide();
            }
            if (fig.getClass() == Round.class) {
                Round round = (Round)fig;
                return "Диаметр круга " + round.getDiameter();
            }
            else
                return "Неизвестная фигура";
        } catch (NullPointerException e) {
            return "Неизвестная фигура";
        }
    }

    public static void main(String[] args) {
        Square square = new Square(5);
        Round round = new Round(10);

        System.out.println(new FigureMethods().figDetect(null));
    }
}
