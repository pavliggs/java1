package ru.progwards.java1.lessons.compare_if_cycles;

public class CyclesGoldenFibo {
    public static boolean containsDigit(int number, int digit) {
        // НЕ оптимальный метод решения
//        /*
//        чтобы узнать содержит ли number цифру digit - необходимо сначала получить все цифры числа number,
//        а их максимально может быть 10 (пример: 1234567890 - число number)
//        поэтому получаем все цифры числа number
//        */
//        int num1 = number / 1000000000;
//        int num2 = (number % 1000000000) / 100000000;
//        int num3 = ((number % 1000000000) % 100000000) / 10000000;
//        int num4 = (((number % 1000000000) % 100000000) % 10000000) / 1000000;
//        int num5 = ((((number % 1000000000) % 100000000) % 10000000) % 1000000) / 100000;
//        int num6 = (((((number % 1000000000) % 100000000) % 10000000) % 1000000) % 100000) / 10000;
//        int num7 = ((((((number % 1000000000) % 100000000) % 10000000) % 1000000) % 100000) % 10000) / 1000;
//        int num8 = (((((((number % 1000000000) % 100000000) % 10000000) % 1000000) % 100000) % 10000) % 1000) / 100;
//        int num9 = ((((((((number % 1000000000) % 100000000) % 10000000) % 1000000) % 100000) % 10000) % 1000) % 100) / 10;
//        int num10 = ((((((((number % 1000000000) % 100000000) % 10000000) % 1000000) % 100000) % 10000) % 1000) % 100) % 10;
//
//        /*
//        есть некая особенность - если число number состоит менее, чем из 10 цифр, то те цифры, которых нехватает становятся 0
//        и встают спереди числа и если мы цифре digit присвоим значение 0, то программа посчитает, что в числе number
//        присутствует 0, хотя его в числе может совсем и не быть
//        поэтому необходимо исключить такие комбинации при помощи нескольких условий
//        */
//        if (num1 == 0 && num2 == 0 && num3 == 0 && num4 == 0 && num5 == 0 && num6 == 0 && num7 == 0 && num8 == 0
//                && num9 == 0 && num10 != 0 && digit == 0)
//            return false;
//        else if (num1 == 0 && num2 == 0 && num3 == 0 && num4 == 0 && num5 == 0 && num6 == 0 && num7 == 0 && num8 == 0
//                && num9 != 0 && num10 != 0 && digit == 0)
//            return false;
//        else if (num1 == 0 && num2 == 0 && num3 == 0 && num4 == 0 && num5 == 0 && num6 == 0 && num7 == 0 && num8 != 0
//                && num9 != 0 && num10 != 0 && digit == 0)
//            return false;
//        else if (num1 == 0 && num2 == 0 && num3 == 0 && num4 == 0 && num5 == 0 && num6 == 0 && num7 != 0 && num8 != 0
//                && num9 != 0 && num10 != 0 && digit == 0)
//            return false;
//        else if (num1 == 0 && num2 == 0 && num3 == 0 && num4 == 0 && num5 == 0 && num6 != 0 && num7 != 0 && num8 != 0
//                && num9 != 0 && num10 != 0 && digit == 0)
//            return false;
//        else if (num1 == 0 && num2 == 0 && num3 == 0 && num4 == 0 && num5 != 0 && num6 != 0 && num7 != 0 && num8 != 0
//                && num9 != 0 && num10 != 0 && digit == 0)
//            return false;
//        else if (num1 == 0 && num2 == 0 && num3 == 0 && num4 != 0 && num5 != 0 && num6 != 0 && num7 != 0 && num8 != 0
//                && num9 != 0 && num10 != 0 && digit == 0)
//            return false;
//        else if (num1 == 0 && num2 == 0 && num3 != 0 && num4 != 0 && num5 != 0 && num6 != 0 && num7 != 0 && num8 != 0
//                && num9 != 0 && num10 != 0 && digit == 0)
//            return false;
//        else if (num1 == 0 && num2 != 0 && num3 != 0 && num4 != 0 && num5 != 0 && num6 != 0 && num7 != 0 && num8 != 0
//                && num9 != 0 && num10 != 0 && digit == 0)
//            return false;
//        else if (num1 == digit || num2 == digit || num3 == digit || num4 == digit || num5 == digit || num6 == digit || num7 == digit
//                || num8 == digit || num9 == digit || num10 == digit)
//            return true;
//        else
//            return false;
        //оптимальный метод решения!
        if (number == 0 && digit == 0)
            return true;
        while (number > 0) {
            int temp = number % 10;
            if (temp == digit)
                return true;
            number /= 10;
        }
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
        System.out.println(containsDigit(112555, 4));

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
