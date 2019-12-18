package ru.progwards.pavliggs;

public class ExampleClass {

    //функция принимает число, а возращает это же число, но только в обратном порядке
    public static int reverseNumber(int number) {
        if (number == 0)
            return 0;
        String str = "";
        while (number > 0) {
            int last = number % 10;
            str += Long.toString(last);
            number /= 10;
        }
        return Integer.parseInt(str);
    }

    public static void main(String[] args) {
        System.out.println(reverseNumber(92232327));
    }
}
