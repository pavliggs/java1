package ru.progwards.java1.testlesson;

import org.telegram.telegrambots.ApiContextInitializer;

public class PizzaBot extends ProgwardsTelegramBot {
    private final String menu = "У нас есть пицца, напитки и десерт"; //константа

    @Override //переопределение функции родительского класса
    public String processMessage(String text) {
        checkTags(text); //функция поиска (отбирает какие строки подходят под запрос пользователя)
        //возвращает количество строк, которое соответствует запросу пользователя
        if (foundCount() == 1) {
            if (checkLastFound("привет"))
                return "Приветствую тебя, о мой повелитель!\n Что желаешь? " + menu;
            if (checkLastFound("конец"))
                return "Спасибо за заказ.";
            if (checkLastFound("дурак"))
                return "Не надо ругаться. Я не волшебник, я только учусь";

            return "Отлично, добавляю в заказ " + getLastFound() + " Желаешь что-то еще?";
        }
        if (foundCount() > 1)
            return "Под твой запрос подходит: \n" + extract() + "Выбери что-то одно, и я добавлю это в заказ.";
        return "Я не понял, возможно у нас этого нет, попробуй сказать по другому." + menu;
    }

    public static void main (String[] args) {
        System.out.println("Hello, Bot!");
        ApiContextInitializer.init(); //инициализация библиотеки

        PizzaBot pizzaBot = new PizzaBot();
        pizzaBot.username = "PizzaTastyBot";
        pizzaBot.token = "695537327:AAHsUdJEcqZ1ZAnQWEb_bqwr8BdD5A2OwJk";

        pizzaBot.addTags("привет", "привет, здрасьте, здравствуй, добр, день, вечер, утро, hi, hello");
        pizzaBot.addTags("конец", "конец, все, стоп, нет, спасибо");
        pizzaBot.addTags("дурак", "дурак, придурок, идиот, тупой");

        pizzaBot.addTags("Пицца гавайская", "гавайск, пицц, ананас, куриц");
        pizzaBot.addTags("Пицца маргарита", "маргарит, пицц, моцарелла, сыр, кетчуп, помидор");
        pizzaBot.addTags("Пицца пеперони", "пеперони, пицц, салями, колбас, сыр, кетчуп, помидор");

        pizzaBot.addTags("Торт тирамису", "десерт, кофе, маскарпоне, бисквит");
        pizzaBot.addTags("Торт медовик", "десерт, мед, бисквит");
        pizzaBot.addTags("Эклеры", "десерт, заварной, крем, тесто");

        pizzaBot.addTags("Кола", "напит, пить, кола");
        pizzaBot.addTags("Холодный чай", "напит, пить, чай, липтон, лимон");
        pizzaBot.addTags("Сок", "напит, пить, сок, апельсиноый, яблочный, вишневый");

        pizzaBot.start();
    }

}
