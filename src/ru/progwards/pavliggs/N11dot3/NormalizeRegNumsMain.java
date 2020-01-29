package ru.progwards.pavliggs.N11dot3;

import java.util.Arrays;

public class NormalizeRegNumsMain {
    private String regNum = "";

    public NormalizeRegNumsMain(String str) {
        //вариант 1 (immutable объект)
        //приводим строку к массиву символов и пробегаемся по этому массиву
//        for (char ch: str.toCharArray()) {
//            //создаем строку только в том случае, если символ является или цифрой или буквой алфавита
//            if (Character.isDigit(ch) || Character.isAlphabetic(ch))
//                regNum += Character.toUpperCase(ch);
//        }

        //вариант 2 (StringBuilder - mutable объект (изменяемый))
        //создадим объект с количеством символов по умолчанию (16 символов)
        StringBuilder stringBuilder = new StringBuilder();
        for (char ch: str.toCharArray()) {
            if (Character.isDigit(ch) || Character.isAlphabetic(ch))
                /*StringBuilder позволяет при конкатенации не создавать новый объект типа String, а добавляет
                строку в существующий объект и является изменяемым объектом
                метод append добавляет строку в конец stringBuilder*/
                stringBuilder.append(Character.toUpperCase(ch));
        }
        //выводим StringBuilder в виде строки
        regNum = stringBuilder.toString();
    }

    //переопределяем для корректного вывода на консоль
    @Override
    public String toString() {
        return regNum;
    }

    public static void main(String[] args) {
        String[] regNumArr = {"b200AC  56", "№ C999cc78", "k  677 aa34", "e 1 2 1 o t", "  ##T  457 # k # m"};

        NormalizeRegNumsMain[] regNums = new NormalizeRegNumsMain[regNumArr.length];

        //каждому объекту типа NormalizeRegNumsMain в качестве параметра присваиваем соответствующий элемент массива regNumArr
        for (int i = 0; i < regNumArr.length; i++) {
            regNums[i] = new NormalizeRegNumsMain(regNumArr[i]);
        }

        System.out.println(Arrays.toString(regNums));
    }
}
