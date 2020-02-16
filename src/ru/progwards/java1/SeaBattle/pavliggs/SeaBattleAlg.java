package ru.progwards.java1.SeaBattle.pavliggs;

import ru.progwards.java1.SeaBattle.SeaBattle;
import ru.progwards.java1.SeaBattle.SeaBattle.FireResult;

import java.util.Arrays;

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

    private static final int MINUS = 0b01; // стреляем в уменьшение координаты
    private static final int PLUS = 0b10; // стреляем в увеличение координаты

    char[][] field;
    SeaBattle seaBattle;
    int hits;
    int direction;
    boolean printField = false;

    void countHits() {
        hits++;
    }

    void print(boolean doPrint) {
        if (!doPrint)
            return;
        for (int y = 0; y < seaBattle.getSizeY(); y++) {
            String str = "|";
            for (int x = 0; x < seaBattle.getSizeX(); x++) {
                str += field[x][y] + "|";
            }
            System.out.println(str);
        }
        System.out.println("----------------------");
    }

    void init(SeaBattle seaBattle) {
        // у класса SeaBattle вытянем экземпляр, чтобы в методы нам не передавать SeaBattle seaBattle
        this.seaBattle = seaBattle;
        hits = 0; // количество точных попаданий
        field = new char[seaBattle.getSizeX()][seaBattle.getSizeY()];
        for (int x = 0; x < seaBattle.getSizeX(); x++) {
            Arrays.fill(field[x], ' ');
        }
    }

    void markFire(int x, int y, SeaBattle.FireResult result) {
        if (result != FireResult.MISS) {
            field[x][y] = 'X';
            countHits();
        }
        else
            field[x][y] = '*';
    }

    void markDot(int x, int y) {
        if (x < 0 || y < 0 || x >= seaBattle.getSizeX() || y >= seaBattle.getSizeY())
            return;
        if (field[x][y] == ' ')
            field[x][y] = '.';
    }

    void markHit(int x, int y) {
        markDot(x - 1, y - 1);
        markDot(x - 1, y);
        markDot(x - 1, y + 1);
        markDot(x + 1, y - 1);
        markDot(x + 1, y);
        markDot(x + 1, y + 1);
        markDot(x, y - 1);
        markDot(x, y + 1);
    }

    void markDestroyed() {
        for (int x = 0; x < seaBattle.getSizeX(); x++) {
            for (int y = 0; y < seaBattle.getSizeY(); y++) {
                if (field[x][y] == 'X')
                    markHit(x, y);
            }
        }
    }

    boolean checkHit(SeaBattle.FireResult result, int fireDerection) {
        switch (result) {
            case DESTROYED:
                direction = 0;
                return true;
            case HIT:
                return false;
            case MISS:
                direction &= ~fireDerection;
                return false;
        }
        return false;
    }

    boolean killHorizontal(int x, int y) {
        int i = 1;
        boolean destroyed = false;
        direction = PLUS | MINUS;
        do {
            if ((direction & MINUS) != 0)
                destroyed = checkHit(fire((x - i), y), MINUS);
            if ((direction & PLUS) != 0)
                destroyed = checkHit(fire((x + i), y), PLUS);
            i++;
        } while (direction != 0);
        return destroyed;
    }

    boolean killVertical(int x, int y) {
        int i = 1;
        boolean destroyed = false;
        direction = PLUS | MINUS;
        do {
            if ((direction & MINUS) != 0)
                destroyed = checkHit(fire(x, (y - i)), MINUS);
            if ((direction & PLUS) != 0)
                destroyed = checkHit(fire(x, (y + i)), PLUS);
            i++;
        } while (direction != 0);
        return destroyed;
    }

    void killShip(int x, int y) {
        boolean destroyed = killHorizontal(x, y);
        if (!destroyed)
            killVertical(x, y);
    }

    SeaBattle.FireResult fireAndKill(int x, int y) {
        SeaBattle.FireResult result = fire(x, y);
        if (result == FireResult.HIT)
            killShip(x, y);
        return result;
    }

    SeaBattle.FireResult fire(int x, int y) {
        if (x < 0 || y < 0 || x >= seaBattle.getSizeX() || y >= seaBattle.getSizeY() ||
                hits >= 20 || field[x][y] != ' ')
            return FireResult.MISS;
        SeaBattle.FireResult result = seaBattle.fire(x, y);
        markFire(x, y, result);
        if (result == FireResult.DESTROYED)
            markDestroyed();
        print(printField);
        return result;
    }

    public void battleAlgorithm(SeaBattle seaBattle) {
        init(seaBattle);
        // стрельба по всем клеткам полным перебором
        for (int y = 0; y < seaBattle.getSizeX(); y++) {
        	for (int x = 0; x < seaBattle.getSizeY(); x++) {
                // стреляем по клетке
                fireAndKill(x, y);
            }
        }
    }

    public static void testFull() {
        System.out.println("Sea battle");
        double result = 0;
        for (int i = 0; i < 1000; i++) {
            SeaBattle seaBattle = new SeaBattle();
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
        System.out.println(seaBattle);
    }

    public static void main(String[] args) {
    	testFull();
//    	testOne();
    }
}

