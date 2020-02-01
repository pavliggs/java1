package ru.progwards.java1.lessons.io2;

public class PhoneNumber {
    public static String format(String phone) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            //проходимся по символам строки phone и записываем в stringBuilder только цифры
            for (char ch: phone.toCharArray()) {
                if (Character.isDigit(ch))
                    stringBuilder.append(ch);
            }
            /*если цифр 11 и первая не равна 7, то заменяем её на 7 и спереди добавляем +
             * а если первая уже является 7, то просто добавляем +*/
            if (stringBuilder.length() == 11) {
                if (stringBuilder.charAt(0) != '7') {
                    stringBuilder.setCharAt(0, '7');
                    stringBuilder.insert(0, "+");
                } else
                    stringBuilder.insert(0, "+");
                //если цифр 10, то просто добавляем спереди +7
            } else if (stringBuilder.length() == 10)
                stringBuilder.insert(0, "+7");
                //если цифр другое количество, то значит номер некорректный
            else {
                throw new Exception("Некорректный номер");
            }
            //подводим наш объект под определенный формат для вывода, вставляя определённые символы в определенные позиции
            stringBuilder.insert(2, "(");
            stringBuilder.insert(6, ")");
            stringBuilder.insert(10, "-");
            return stringBuilder.toString();
        } catch (Exception e) {
            System.out.println(e);
            return "";
        }
    }

    public static void main(String[] args) {
        System.out.println(format("953)939-2227223   38"));
    }
}
