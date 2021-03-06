package ru.progwards.java1.lessons.io2;

public class PhoneNumber {
    public static String format(String phone) throws RuntimeException {
        StringBuilder stringBuilder = new StringBuilder();
        //проходимся по символам строки phone и записываем в stringBuilder только цифры
        for (char ch: phone.toCharArray()) {
            if (Character.isDigit(ch))
                stringBuilder.append(ch);
        }
        /* если цифр 11 и первая равна 7, то спереди добавляем +
         * если первая равна 8, то заменяем её на 7 и спереди добавляем +
         * если другая цифра, то бросаем RuntimeException */
        if (stringBuilder.length() == 11) {
            if (stringBuilder.charAt(0) == '7') {
                stringBuilder.insert(0, "+");
            } else if (stringBuilder.charAt(0) == '8') {
                stringBuilder.setCharAt(0, '7');
                stringBuilder.insert(0, "+");
            } else
                throw new RuntimeException();
        //если цифр 10, то просто добавляем спереди +7
        } else if (stringBuilder.length() == 10)
            stringBuilder.insert(0, "+7");
        //если цифр другое количество, то значит номер некорректный (выбросим непроверяемое исключение)
        else {
            throw new RuntimeException();
        }
        //подводим наш объект под определенный формат для вывода, вставляя определённые символы в определенные позиции
        stringBuilder.insert(2, "(");
        stringBuilder.insert(6, ")");
        stringBuilder.insert(10, "-");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(format("5953939273"));
    }
}
