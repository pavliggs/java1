package ru.progwards.java1.project_telegram_bot;

import org.telegram.telegrambots.ApiContextInitializer;

import java.util.*;

public class PizzaBot extends TelegramBot {
    private final String MENU = "У нас есть пицца, картошка, напитки и десерты.";

    private static final String ORDER_KEY = "orderKey";
    private static final String ADDRESS_KEY = "address";
    private static final String GROUPS = "groups";
    private static final String OFFER_ADD_TO_ORDER = "offerAddToOrder";
    private static final String OFFER_ADD_GROUPS = "offerAddGroups";

    // сохраняем количество позиций в заказе
    int saveOrderKey(Integer userId) {
        String str = (String) getUserData(userId, ORDER_KEY);
        Integer count = 0;
        if (str != null)
            count = Integer.parseInt(str);
        ++count;
        setUserData(userId, ORDER_KEY, count.toString());
        return count;
    }

    // сохраняем позиции заказа и формируем множество из групп заказанных продуктов, если в tags.tags содержится 1 элемент
    String saveOrderItem(Integer userId, FoundTags tags) {
        int count = saveOrderKey(userId);
        setUserData(userId, "order" + count, getNameLastFound(tags));
        if (getUserData(userId, GROUPS) == null) {
            Set<String> groups = new HashSet<>();
            groups.add(getGroupLastFound(tags));
            setUserData(userId, GROUPS, groups);
        } else {
            Set<String> set = (Set<String>) getUserData(userId, GROUPS);
            set.add(getGroupLastFound(tags));
        }
        return "Отлично! Добавил в твой заказ.\nДружище, может что-то ещё?\n" + MENU;
    }

    // сохраняем позиции заказа и формируем множество из групп заказанных продуктов
    String saveOrderItemAdd(Integer userId, FoundTags tags) {
        int count = saveOrderKey(userId);
        setUserData(userId, "order" + count, getNameFound(tags));
        if (getUserData(userId, GROUPS) == null) {
            Set<String> groups = new HashSet<>();
            groups.add(getGroupCashMap());
            setUserData(userId, GROUPS, groups);
        } else {
            Set<String> set = (Set<String>) getUserData(userId, GROUPS);
            set.add(getGroupCashMap());
        }
        return "Отлично! Добавил в твой заказ.\nДружище, может что-то ещё?\n" + MENU;
    }

    String finishCheck(Integer userId, String text) {
        if (getUserData(userId, OFFER_ADD_TO_ORDER) == null) {
            setUserData(userId, OFFER_ADD_TO_ORDER, "*");
            // result - множество из всех групп основных продуктов
            Set<String> result = new HashSet<>(getAllMainGroups());
            // groupsOrder - множество из всех групп продуктов, добавленных в заказ
            Set<String> groupsOrder = (Set<String>) getUserData(userId, GROUPS);
            // оставим в result только те группы, которых нет во множестве groupsOrder
            // если result не пустое, то группы которые остались в result предложить заказать пользователю
            result.removeAll(groupsOrder);
            if (!result.isEmpty())
                return "Дружище, могу предложить тебе добавить в свой заказ:\n" + outputColumn(result) + "\n" +
                        "Что-то из этого желаешь?";
        }
        if (getUserData(userId, ADDRESS_KEY) == null) {
            setUserData(userId, ADDRESS_KEY, "*");
            return "Дружище, пиши свой адрес";
        }
        setUserData(userId, ADDRESS_KEY, text);
        String order = "Твой заказ: " + getFullOrder(userId);
        String adress = "Адрес доставки: " + getUserData(userId, ADDRESS_KEY);
        return "Итого:\n" + order + "\n" + adress + "\n" + "Спасибо за заказ, дружище!";
    }

    // вывести заказ на экран
    String getFullOrder(Integer userId) {
        int count = Integer.parseInt((String) getUserData(userId, ORDER_KEY));
        String fullOrder = "";
        for (int i = 1; i <= count; i++) {
            if (i == count)
                fullOrder += getUserData(userId, "order" +  i);
            else
                fullOrder += getUserData(userId, "order" + i) + ", ";
        }
        return fullOrder;
    }

    // вывести группы заказанных продуктов на экран
    String getGroups(Integer userId) {
        Set<String> groups = (Set<String>) getUserData(userId, GROUPS);
        return outputColumn(groups);
    }

    // получаем элементы множества в столбик
    String outputColumn(Set<String> set) {
        String res = "";
        int count = 0;
        for (String elem : set) {
            ++count;
            if (count == set.size())
                res += elem;
            else
                res += elem + "\n";
        }
        return res;
    }

    // содержит ли строка цифру
    boolean containsDigit(String str) {
        boolean result = false;
        for (char ch : str.toCharArray()) {
            if (Character.isDigit(ch)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public String processMessage(Integer userId, String text) {


        // сброс данных
        if (text.equals("/reset"))
            cleartUserData(userId);

        FoundTags tags = checkTags(text);

        if (containsDigit(text))
            return finishCheck(userId, text);

        if (foundCount(tags) == 1) {
            if (checkLastFound(tags, "привет"))
                return "Привет, дружище! Что желаешь?\n" + MENU;
            if (checkLastFound(tags, "заказ"))
                return "Дружище, вот твой заказ:\n" + getFullOrder(userId);
            if (checkLastFound(tags, "группы блюд"))
                return getGroups(userId);
            if (checkLastFound(tags, "конец"))
                return finishCheck(userId, text);
            return saveOrderItem(userId, tags);
        }
        if (foundCount(tags) > 1) {
            if (containsWeightEqualTen(tags))
                return saveOrderItemAdd(userId, tags);
            return "Дружище, под твой запрос подходит:\n" + extract(tags)
                    + "Выбери что-то одно, напиши полное название и я добавлю это в твой заказ";
        }
        return "Не понял тебя, дружище. Возможно у нас этого нет или в твоём запросе ошибка(( Попробуй повторить\n" + MENU;
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
        bot.addTags("конец", "всё, все, стоп, нет, хватит, больше ничего, не нужно, не хочу, закончил, достаточно");

        bot.addTags("заказ", "заказ");
        bot.addTags("группы блюд", "групп");

        // пиццы в наличии
        bot.addTags("Пицца гавайская", "гавайск, пицц, ананас, ветчин, моцарел, сыр, помидор, томат, кетчуп", bot.addGroup("пицца"));
        bot.addTags("Пицца маргарита", "маргарит, пицц, моцарел, сыр, томат, помидор, базилик, орегано", bot.addGroup("пицца"));
        bot.addTags("Пицца пепперони", "пеперони, пепперони, моцарел, сыр, пицц, салями, колбас, чили, " +
                "перец, помидор, томат, орегано, базилик, чесно", bot.addGroup("пицца"));
        bot.addTags("Пицца Четыре сыра", "пицц, моцарел, сыр, четыре", bot.addGroup("пицца"));
        bot.addTags("Пицца мясная", "пицц, моцарел, сыр, томат, помидор, ветчин, колбас, кури, мяс", bot.addGroup("пицца"));
        bot.addTags("Пицца Ветчина и сыр", "пицц, моцарел, сыр, ветчин, колбас, ветчина и сыр", bot.addGroup("пицца"));

        // десерты в наличии
        bot.addTags("Тирамису", "десерт, кофе, маскарпоне, бисквит, сладк, тирамис, торт, пирожн", bot.addGroup("десерт"));
        bot.addTags("Медовик", "десерт, мед, бисквит, сладк, медовик, торт", bot.addGroup("десерт"));
        bot.addTags("Эклер", "десерт, заварн, крем, сладк, эклер, пирожн", bot.addGroup("десерт"));
        bot.addTags("Мороженое Пломбир", "морож, сливочн, пломбир, сладк", bot.addGroup("десерт"));

        // напитки в наличии
        bot.addTags("Кола", "напит, пить, кола, coca, cola, лимонад, газировк", bot.addGroup("напиток"));
        bot.addTags("Фанта", "напит, пить, фанта, fanta, лимонад, газировк", bot.addGroup("напиток"));
        bot.addTags("Холодный чай", "напит, пить, чай, липтон, лимон, холодн", bot.addGroup("напиток"));
        bot.addTags("Сок яблочный", "сок, напит, пить, ябло, фруктов, натуральн", bot.addGroup("напиток"));
        bot.addTags("Сок апельсиновый", "сок, напит, пить, апельсин, фруктов, натуральн", bot.addGroup("напиток"));
        bot.addTags("Сок вишнёвый", "сок, напит, пить, вишн, ягод, натуральн", bot.addGroup("напиток"));

        // картошка
        bot.addTags("Картофель фри", "картофел, картошк, фри", bot.addGroup("картошка"));
        bot.addTags("Картофель Айдахо", "картофел, картошк, айдахо", bot.addGroup("картошка"));

        // соусы
        bot.addTags("Соус сырный", "соус", bot.addGroup("соус картошка"));
        bot.addTags("Соус барбекю", "соус, барбекю, кетчуп", bot.addGroup("соус картошка"));

//        bot.start();
        bot.test();

    }
}
