package ru.progwards.java1.lessons.basics;

public class ReverseDigits {
    public static int reverseDigits(int number) {
        //получим последнюю цифру трехзначного числа
        int num1 = (number % 100) % 10;
        //получим вторую цифру трехзначного числа
        int num2 = (number % 100) / 10;
        //получим первую цифру трехзначного числа
        int num3 = number / 100;
        //каждую полученную цифру конвертировать в тип данных String и произвести конкатенацию
        String str1 = Integer.toString(num1);
        String str2 = Integer.toString(num2);
        String str3 = Integer.toString(num3);
        String str = str1 + str2 + str3;
        //результат конкатенации конвертировать обратно в тип данных int - это и будет результатом функции
        return Integer.parseInt(str);
    }

    public static void main(String[] args) {
        System.out.println(reverseDigits(123));
    }
}
