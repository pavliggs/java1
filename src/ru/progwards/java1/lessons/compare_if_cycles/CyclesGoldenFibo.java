package ru.progwards.java1.lessons.compare_if_cycles;

public class CyclesGoldenFibo {
    public static boolean containsDigit(int number, int digit) {
        /*
        чтобы узнать содержит ли десятичное представление числа number цифру digit - необходимо
        сначала получить первую и вторую цифры числа number, а затем сравнить эти цифры с числом digit
        */
        if (number % 10 == digit || number / 10 == digit)
            return true;
        else
            return false;
    }

    public static int fiboNumber(int n) {
        //присвоим переменным fibo1 и fibo2 значения первых двух чисел последовательности Фибоначчи
        int fibo1 = 1;
        int fibo2 = 1;
        //цикл будем начинать с i = 2, т.к. значения первых двух чисел у нас уже имеются
        for (int i = 2; i < n; i++) {
            int fiboNext = fibo1 + fibo2;
            //путём присваивания сдвигаем вперёд значения fibo1 и fibo2 в последовательности Фибоначчи
            fibo1 = fibo2;
            fibo2 = fiboNext;
        }
        return fibo2;
    }

    public static boolean isGoldenTriangle(int a, int b, int c) {
        /*
        при помощи функции isTriangle добавим дополнительное условие, которое будет возвращать false
        и соответствующее сообщение, если из таких сторон нельзя построить треугольник
        */
        if (TriangleInfo.isTriangle(a, b, c)) {
            //условия, которые соответствуют правилу Золотого треугольника
            if ((a == b && (double)a / c >= 1.61703 && (double)a / c <= 1.61903) ||
                    (a == c && (double)a / b >= 1.61703 && (double)a / b <= 1.61903) ||
                            (b == c && (double)b / a >= 1.61703 && (double)b / a <= 1.61903)) {
                return true;
            } else
                return false;
        } else {
            System.out.println("Не удается построить треугольник по данным сторонам");
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(containsDigit(64, 6));
        //при помощи цикла выведем на консоль первые 15 чисел последовательности Фибоначчи
        for (int i = 1; i <= 15; i++) {
            System.out.print(fiboNumber(i) + " ");
        }

        //отступим строку
        System.out.println("\n");

        //найдём при помощи встроенных циклов Золотые треугольники со сторонами из натуральных чисел не превышающих 100
        for (int a = 1; a <= 100; a++) {
            for (int b = 1; b <= 100; b++) {
                for (int c = 1; c <= 100; c++) {
                    /*
                    подставляем комбинации чисел a, b, c в функции ниже и если вернётся true, то выведем эти комбинации,
                    которые и будут являтся сторонами Золотого треугольника
                    */
                    if (TriangleInfo.isTriangle(a, b, c) && isGoldenTriangle(a, b, c)) {
                        System.out.print("Сторона a = " + a + " ");
                        System.out.print("Сторона b = " + b + " ");
                        System.out.print("Сторона c = " + c + " " + "\n");
                    }
                }
            }
        }
    }
}
