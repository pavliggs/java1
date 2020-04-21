package ru.progwards.java1.project_telegram_bot;

import org.telegram.telegrambots.ApiContextInitializer;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class PizzaBot extends TelegramBot {
    private Path inDirectory;

    private final String MENU = "У нас есть пицца, картошка, напитки и десерты.";

    private static final String ORDER_KEY = "orderKey";
    private static final String ADDRESS_KEY = "address";
    private static final String PRICES = "prices";
    private static final String GROUPS = "groups";
    private static final String OFFER_ADD_TO_ORDER = "offerAddToOrder";
    private static final String SUGGEST_ADDITIONAL_GROUPS = "offerAddGroups";

    PizzaBot (String inFile) {
        this.inDirectory = Paths.get(inFile);
    }

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

    // сохраняем позиции заказа и формируем множество из групп заказанных продуктов, если foundCount(tags) == 1
    String saveOrderItem(Integer userId, FoundTags tags) {
        int count = saveOrderKey(userId);
        setUserData(userId, "order" + count, getNameLastFound(tags));
        if (getUserData(userId, PRICES) == null) {
            List<Integer> prices = new ArrayList<>();
            prices.add(Integer.parseInt(getPriceLastFound(tags)));
            setUserData(userId, PRICES, prices);
        } else {
            List<Integer> prices = (List<Integer>) getUserData(userId, PRICES);
            prices.add(Integer.parseInt(getPriceLastFound(tags)));
        }
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

    // сохраняем позиции заказа и формируем множество из групп заказанных продуктов, если foundCount(tags) > 1
    String saveOrderItemFull(Integer userId, FoundTags tags) {
        int count = saveOrderKey(userId);
        setUserData(userId, "order" + count, getNameFound(tags));
        if (getUserData(userId, PRICES) == null) {
            List<Integer> prices = new ArrayList<>();
            prices.add(Integer.parseInt(getPriceCashMap()));
            setUserData(userId, PRICES, prices);
        } else {
            List<Integer> prices = (List<Integer>) getUserData(userId, PRICES);
            prices.add(Integer.parseInt(getPriceCashMap()));
        }
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
        // предлагаем дозаказать группы товаров, которых нет в заказе
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
                return "Дружище, хочу предложить тебе добавить в свой заказ:\n" + outputColumn(result) +
                        "Если что-то из этого желаешь, то пиши что именно хочешь дозаказать, если " +
                        "ничего больше не нужно, то напиши слово \"нет\"";
        }
        /* предлагаем заказать дополнительные группы товаров к товарам, присутствующим в заказе
         * (если товары, присутствующие в заказе подразумевают дололнительные товары) */
        if (getUserData(userId, SUGGEST_ADDITIONAL_GROUPS) == null) {
            setUserData(userId, SUGGEST_ADDITIONAL_GROUPS, "*");
            // groupsOrder - множество из всех групп продуктов, добавленных в заказ
            Set<String> groupsOrder = (Set<String>) getUserData(userId, GROUPS);
            // foundAdditionalGroups - словарь из дополнительных групп товаров, которые подходят к товарам в заказе
            Map<String, String> foundAdditionalGroups = getMapFoundAdditionalGroups(getAllAdditionalGroups(), groupsOrder);
            if (!foundAdditionalGroups.isEmpty()) {
                return suggestAdditionalGroups(foundAdditionalGroups);
            }
        }
        if (getUserData(userId, ADDRESS_KEY) == null) {
            setUserData(userId, ADDRESS_KEY, "*");
            return "Дружище, пиши свой адрес";
        }
        setUserData(userId, ADDRESS_KEY, text);
        String order = "Твой заказ: " + getFullOrder(userId) + "\n";
        String orderPrice = "Сумма заказа: " + getOrderPrice(userId) + "\n";
        String address = "Адрес доставки: " + getUserData(userId, ADDRESS_KEY) + "\n";
        return "Итого:\n" + order + orderPrice + address + "Спасибо за заказ, дружище!";
    }

    // формируем список основных товаров, имеющихся в заказе и список дополнительных к ним товаров
    String suggestAdditionalGroups(Map<String, String> map) {
        Set<Map.Entry<String, String>> entries = map.entrySet();
        String result1 = "";
        String result2 = "";
        for (Map.Entry<String, String> entry : entries) {
            result1 += entry.getKey() + "\n";
            result2 += entry.getValue() + "\n";
        }
        return "Дружище, в твоём заказе присутствуют(ет):\n" + result1 +
                "Дополнительно к этому предлагаю заказать:\n" + result2 +
                "Если что-то из этого желаешь, то пиши что именно хочешь дозаказать, если " +
                "ничего больше не нужно, то напиши слово \"нет\"";
    }

    // получить Map<String, String> из найденных дополнительных групп товаров к товарам в заказе
    Map<String, String> getMapFoundAdditionalGroups(Map<String, String> map, Set<String> set) {
        Map<String, String> mapResult = new HashMap<>(map);
        Set<Map.Entry<String, String>> setResult = mapResult.entrySet();
        Iterator<Map.Entry<String, String>> setResultIterator = setResult.iterator();

        while (setResultIterator.hasNext()) {
            Map.Entry<String, String> entry = setResultIterator.next();
            /* например: картошка|соус
             * если значение (соус) содержится в заказанных группах товаров, то удаляем entry
             * или если ключ (картошка) не содержится в заказанных группах товаров - тоже удаляем entry */
            if (set.contains(entry.getValue()) || !set.contains(entry.getKey()))
                setResultIterator.remove();
        }

        return mapResult;
    }

    // вывести заказ на экран
    String getFullOrder(Integer userId) {
        int count = Integer.parseInt((String) getUserData(userId, ORDER_KEY));
        String fullOrder = "";
        for (int i = 1; i <= count; i++) {
            if (i == count)
                fullOrder += getUserData(userId, "order" +  i) + " - " + ((List<Integer>) getUserData(userId, PRICES)).get(i - 1);
            else
                fullOrder += getUserData(userId, "order" + i) + " - " + ((List<Integer>) getUserData(userId, PRICES)).get(i - 1) + ", ";
        }
        return fullOrder;
    }

    // получить сумму заказа
    int getOrderPrice(Integer userId) {
        int result = 0;
        List<Integer> prices = (List<Integer>) getUserData(userId, PRICES);
        for (Integer price : prices) {
            result += price;
        }
        return result;
    }

    // получаем элементы множества в столбик
    String outputColumn(Set<String> set) {
        String res = "";
        for (String elem : set) {
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

        // если текст пользователя содержит цифру, то значит он ввёл свой адрес
        if (containsDigit(text))
            return finishCheck(userId, text);

        if (foundCount(tags) == 1) {
            if (checkLastFound(tags, "привет"))
                return "Привет, дружище! Что желаешь?\n" + MENU;
            if (checkLastFound(tags, "заказ"))
                return "Дружище, вот твой заказ:\n" + getFullOrder(userId);
            if (checkLastFound(tags, "конец"))
                return finishCheck(userId, text);
            return saveOrderItem(userId, tags);
        }
        if (foundCount(tags) > 1) {
            if (containsWeightEqualTen(tags))
                return saveOrderItemFull(userId, tags);
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

        PizzaBot bot = new PizzaBot("C:\\Users\\Эльдорадо\\Desktop\\TestJava\\pizzaBot\\inFolder");
        bot.username = "PizzaTastyBot";
        bot.token = "695537327:AAHsUdJEcqZ1ZAnQWEb_bqwr8BdD5A2OwJk";

        bot.addTags(bot.inDirectory);

//        bot.start();
        bot.test();
    }
}
