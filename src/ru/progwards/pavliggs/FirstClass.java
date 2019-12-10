package ru.progwards.pavliggs;

public class FirstClass {
    static void helloWorld() {
        System.out.println("Hello, World!");
    }

    static void println(String str) {
        System.out.println(str);
    }

    static int addition(int x, int y) {
        return x + y;
    }

    static double fractional(double num) {
        return num - Math.floor(num);
    }

    public static void main(String[] args) {
        helloWorld(); //вызов функции
        FirstClass.helloWorld(); //вызов функции, используя имя класса (обязательно, если хотим вызвать из другого класса)
        ru.progwards.pavliggs.FirstClass.helloWorld(); //вызов функции, используя имя пакета (обязательно, если хотим вызвать из другого пакета,
        //но у функции должнен присутствовать модификатор public, а если его нет, то функцию можно вызвать только из данного пакета

        println("- How are you?");
        println("- Everything is OK!");

        System.out.print("Сумма цифр = ");
        System.out.println(addition(7, 24));

        byte b1 = 123;
        short s1 = 32023;
        int i1 = 65432;
        long l1 = 123456789012345L;
        float f1 = 1.22278F;
        double pi = 3.1415926535;
        float koe = 1.5e7F;
        String message = "Запись фильма \"Матрица\" находится в файле C:\\video\\matrix.avi";
        System.out.println(message);
        int x = 5 / 2;
        System.out.println(x);

        Integer int1 = 5;
        Integer int2 = Integer.valueOf("999");
        Double dou1 = Double.valueOf("777");
        System.out.println(int1);
        System.out.println(int2);
        System.out.println(dou1);


        String s10 = "444";
        String s20 = "0002223333";
        String s30 = "469";

        Integer i10 = Integer.valueOf(s10);
        Integer i20 = Integer.valueOf(s20);
        int i200 = Integer.valueOf(s10); //срабатывает автораспаковка
        int i30 = Integer.parseInt(s30);

        System.out.println(i10);
        System.out.println(i20);
        System.out.println(i200);
        System.out.println(i30);

        int in1 = 567;
        Integer in2 = 345;

        String str1 = Integer.toString(in1);
        String str2 = Integer.toString(in2); //срабатывает автораспаковка
        String str3 = in2.toString();

        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);

        fractional(1.53);
    }
}
