package ru.progwards.pavliggs;

public class NumberSystem {
    //получение цифры в строковом представлении от 0 до 15
    public static String val2digit(int val) {
        switch (val) {
            case 10: return "A";
            case 11: return "B";
            case 12: return "C";
            case 13: return "D";
            case 14: return "E";
            case 15: return "F";
            default: return Integer.toString(val);
        }
    }

    //получение результата в системе счисления от двоичной до шестнадцатиричной
    public static String show(int val, int base) {
        String result = "";
        while (val > 0) {
            int remainder = val % base;
            result = val2digit(remainder) + result;
            val /= base;
        }

        //если переменная result ничего не содержит, то возвращается 0
        if (result.isEmpty())
            return "0";
        return result;
    }

    public static void main(String[] args) {
        int val = 125;
        System.out.println("Десятичная система: " + val);
        System.out.println("Двоичная система: " + show(val, 2));
        System.out.println("Шестнадцатиричная система: " + show(val, 16));
    }
}
