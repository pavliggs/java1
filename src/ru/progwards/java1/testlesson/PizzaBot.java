package ru.progwards.java1.testlesson;

import org.telegram.telegrambots.ApiContextInitializer;
import ru.progwards.java1.telegrambot.ProgwardsTelegramBot;
import java.util.Scanner;

public class PizzaBot extends ProgwardsTelegramBot {
   private final String MENU = "У нас есть пицца, напитки и десерты.";

    private static final String ORDER_KEY = "order";
    private static final String ADRESS_KEY = "address";

    private boolean stop = false;

//    String finishCheck(Integer userId) {
//
//    }

    String getOrder(Integer userId) {
        int count = Integer.parseInt(getUserData(userId, ORDER_KEY));
        String fullOrder = "";
        for (int i = 0; i < count; i++) {
            if (i == count - 1)
                fullOrder += getUserData(userId, "order" + (i + 1));
            else
                fullOrder += getUserData(userId, "order" + (i + 1)) + "\n";
        }
        return "Дружище, вот твой заказ:\n" + fullOrder;
    }

    void saveOrderItem(Integer userId, FoundTags tags) {
        String str = getUserData(userId, ORDER_KEY);
        Integer count = 0;
        if (str != null)
            count = Integer.parseInt(str);
        ++count;
        setUserData(userId, ORDER_KEY, count.toString());
        setUserData(userId, "order" + count, getLastFound(tags));
    }

    @Override
    public String processMessage(Integer userId, String text) {


        // сброс данных
        if (text.equals("/reset"))
            cleartUserData(userId);

        FoundTags tags = checkTags(text);
        if (foundCount(tags) == 1) {
            if (checkLastFound(tags, "привет"))
                return "Привет, дружище! Что желаешь?\n" + MENU;
            if (checkLastFound(tags, "заказ"))
                return getOrder(userId);
            saveOrderItem(userId, tags);
            return "Отлично! Добавил в твой заказ.\nДружиже, может что-то ещё?\n" + MENU;
        }
        if (foundCount(tags) > 1)
            return "Дружище, под твой запрос подходит:\n" + extract(tags)
                    + "Выбери что-то одно и я добавлю это в твой заказ";
        return "Не понял тебя, дружище. Возможно у нас этого нет((. Попробуй повторить запрос\n" + MENU;
    }

    void test() {
        try (Scanner in = new Scanner(System.in)) {
            String inputMessage = in.nextLine();
            while (!inputMessage.contains("/stop")) {
                System.out.println(processMessage(123, inputMessage));
                inputMessage = in.nextLine();
            }
        }
    }

    public static void main(String[] args) {
        ApiContextInitializer.init();

        PizzaBot bot = new PizzaBot();
        bot.username = "PizzaTastyBot";
        bot.token = "695537327:AAHsUdJEcqZ1ZAnQWEb_bqwr8BdD5A2OwJk";

        bot.addTags("привет", "привет, здарова, здорово, здарово, здорова, здрасьте, хай, салют, " +
                            "здравствуйте, приветствую, вечер, день, утро, hello, hi");
        bot.addTags("конец", "всё, все, стоп, нет, хватит, больше ничего, не нужно, не хочу");

        bot.addTags("заказ", "заказ");
//        bot.addTags("нет", "нет");

        // пиццы в наличии
        bot.addTags("Пицца гавайская", "гавайск, пицц, ананас, куриц");
        bot.addTags("Пицца маргарита", "маргарит, пицц, моцарелла, сыр, кетчуп, помидор");
        bot.addTags("Пицца пепперони", "перец, чили, пицц, салями, пепперони, колбаса, сыр, кетчуп, " +
                            "пеперони");

        // десерты в наличии
        bot.addTags("Торт тирамису", "десерт, кофе, маскарпоне, бисквит, сладк, тирамис");
        bot.addTags("Торт медовик", "десерт, мед, бисквит, сладк, медовик");
        bot.addTags("Эклер", "десерт, заварн, крем, тесто, сладк, эклер, пирожн");

        // напитки в наличии
        bot.addTags("Кола", "напит, пить, кола, лимонад, газировк");
        bot.addTags("Холодный чай", "напит, пить, чай, липтон, лимон, холодн");
        bot.addTags("Сок", "напит, пить, сок, апельсиноый, яблочный, вишневый, фруктов, натуральн");

//        bot.start();
        bot.test();
    }
}
