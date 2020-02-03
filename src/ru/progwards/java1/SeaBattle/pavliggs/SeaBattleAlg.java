package ru.progwards.java1.SeaBattle.pavliggs;

import ru.progwards.java1.SeaBattle.SeaBattle;
import ru.progwards.java1.SeaBattle.SeaBattle.FireResult;

public class SeaBattleAlg {

    // Тестовое поле создаётся конструктором
    //     SeaBattle seaBattle = new SeaBattle(true);
    //
    // Обычное поле создаётся конструктором по умолчанию:
    //     SeaBattle seaBattle = new SeaBattle();
    //     SeaBattle seaBattle = new SeaBattle(false);
    //
    // Посомтреть результаты стрельбы можно в любой момент,
    // выведя объект класса SeaBattle на консоль. Например так:
    //     System.out.println(seaBattle);
    //
    //
    // Вид тестового поля:
    //
    //           0 1 2 3 4 5 6 7 8 9    координата x
    //         0|.|.|.|.|.|.|.|X|.|.|
    //         1|.|.|.|.|.|X|.|.|.|.|
    //         2|X|X|.|.|.|.|.|.|.|.|
    //         3|.|.|.|.|.|.|.|X|X|X|
    //         4|.|.|.|.|X|.|.|.|.|.|
    //         5|.|.|.|.|X|.|.|Х|.|.|
    //         6|.|.|.|.|.|.|.|Х|.|X|
    //         7|X|.|X|.|.|.|.|Х|.|X|
    //         8|X|.|.|.|.|.|.|X|.|.|
    //         9|X|.|.|.|X|.|.|.|.|.|

    // метод реализует заполнение клеток точками вокруг 1-палубного корабля
    static void writeToArrayFor1Ship(char[][] charArr, int y, int x, SeaBattle seaBattle) {
        // если корабль находится внутри поля
        if (y > 0 && y < seaBattle.getSizeX() - 1 && x > 0 && x < seaBattle.getSizeY() - 1) {
            charArr[y][x + 1] = '.';
            charArr[y + 1][x + 1] = '.';
            charArr[y + 1][x] = '.';
            charArr[y + 1][x - 1] = '.';
            charArr[y][x - 1] = '.';
            charArr[y - 1][x - 1] = '.';
            charArr[y - 1][x] = '.';
            charArr[y - 1][x + 1] = '.';
        // если корабль находится в левом верхнем углу
        } else if (y == 0 && x == 0) {
            charArr[y][x + 1] = '.';
            charArr[y + 1][x + 1] = '.';
            charArr[y + 1][x] = '.';
        // если корабль примыкает к верхней границе поля
        } else if (y == 0 && x > 0 && x < seaBattle.getSizeY() - 1) {
            charArr[y][x + 1] = '.';
            charArr[y + 1][x + 1] = '.';
            charArr[y + 1][x] = '.';
            charArr[y + 1][x - 1] = '.';
            charArr[y][x - 1] = '.';
        // если корабль находится в правом верхнем углу
        } else if (y == 0 && x == seaBattle.getSizeY() - 1) {
            charArr[y + 1][x] = '.';
            charArr[y + 1][x - 1] = '.';
            charArr[y][x - 1] = '.';
        // если корабль примыкает к правой границе поля
        } else if (y > 0 && y < seaBattle.getSizeX() - 1 && x == seaBattle.getSizeY() - 1) {
            charArr[y + 1][x] = '.';
            charArr[y + 1][x - 1] = '.';
            charArr[y][x - 1] = '.';
            charArr[y - 1][x - 1] = '.';
            charArr[y - 1][x] = '.';
        // если корабль находится в правом нижнем углу
        } else if (y == seaBattle.getSizeX() - 1 && x == seaBattle.getSizeY() - 1) {
            charArr[y][x - 1] = '.';
            charArr[y - 1][x - 1] = '.';
            charArr[y - 1][x] = '.';
        // если корабль примыкает к нижней границе поля
        } else if (y == seaBattle.getSizeX() - 1 && x > 0 && x < seaBattle.getSizeY() - 1) {
            charArr[y][x - 1] = '.';
            charArr[y - 1][x - 1] = '.';
            charArr[y - 1][x] = '.';
            charArr[y - 1][x + 1] = '.';
            charArr[y][x + 1] = '.';
        // если корабль находится в левом нижнем углу
        } else if (y == seaBattle.getSizeX() - 1 && x == 0) {
            charArr[y - 1][x] = '.';
            charArr[y - 1][x + 1] = '.';
            charArr[y][x + 1] = '.';
        // если корабль примыкает к левой границе поля
        } else {
            charArr[y - 1][x] = '.';
            charArr[y - 1][x + 1] = '.';
            charArr[y][x + 1] = '.';
            charArr[y + 1][x + 1] = '.';
            charArr[y + 1][x] = '.';
        }
    }


    public void battleAlgorithm(SeaBattle seaBattle) {
        // двумерный массив (матрица), исполняющий роль поля с кораблями противника
        char[][] field = new char[seaBattle.getSizeY()][seaBattle.getSizeX()];
        for (int i = 0; i < seaBattle.getSizeY(); i++) {
            for (int j = 0; j < seaBattle.getSizeX(); j++) {
                field[i][j] = ' ';
            }
        }

        // попадания
        int hits = 0;
        for (int y = 0; y < seaBattle.getSizeX(); y++) {
        	for (int x = 0; x < seaBattle.getSizeY(); x++) {
                // если ячейка в массиве равна '.' , то не простреливаем эту позицию (переходим к следующей итерации)
                if (field[y][x] == '.')
                    continue;

                // стреляем по клетке
        		SeaBattle.FireResult fireResult = seaBattle.fire(x, y);

        		// если корабль 1палубный, то он убивается с первого выстрела
                // помечаем точками клетки вокруг этого корабля
                if (fireResult == FireResult.DESTROYED) {
                    writeToArrayFor1Ship(field, y, x, seaBattle);
                    x += 1;
                }

                if (fireResult == FireResult.HIT) {
                    if ((x + 1) < seaBattle.getSizeY() - 1 && seaBattle.fire(x + 1, y) == FireResult.DESTROYED) {
                        if (y > 0 && y < seaBattle.getSizeX() - 1 && x > 0) {
                            field[y][x + 2] = '.';
                            field[y + 1][x + 2] = '.';
                            field[y + 1][x + 1] = '.';
                            field[y + 1][x] = '.';
                            field[y + 1][x - 1] = '.';
                            field[y][x - 1] = '.';
                            field[y - 1][x - 1] = '.';
                            field[y - 1][x] = '.';
                            field[y - 1][x + 1] = '.';
                            field[y - 1][x + 2] = '.';
                        }
                    }
                }

                // если мы попали во все клетки с кораблями, то выходим из функции
                if (fireResult != FireResult.MISS)
                    hits++;
                if (hits >= 20)
                    return;
            }
        }
    }

    public static void testFull() {
        System.out.println("Sea battle");
        double result = 0;
        for (int i = 0; i < 1000; i++) {
            SeaBattle seaBattle = new SeaBattle(true);
            new SeaBattleAlg().battleAlgorithm(seaBattle);
            result += seaBattle.getResult();
        }
        System.out.println(result / 1000);
    }

    public static void testOne() {
        System.out.println("Sea battle");
        SeaBattle seaBattle = new SeaBattle(true);
        new SeaBattleAlg().battleAlgorithm(seaBattle);
        System.out.println(seaBattle.getResult());
    }

    public static void main(String[] args) {
    	testFull();
    }
}

