package ru.progwards.java1.project_telegram_bot;

import com.google.common.collect.TreeMultimap;
import org.telegram.telegrambots.ApiContextInitializer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class PizzaBot extends TelegramBot {
    private Path inDirectory;
    private Path outDirectory;

    private final String MENU = "У нас есть пицца, картошка, напитки и десерты.";

    private static final String ORDER_KEY = "orderKey";
    private static final String ADDRESS_KEY = "address";
    private static final String ORDER = "order";
    private static final String QUANTITY = "quantity";
    private static final String PRICES = "prices";
    private static final String GROUPS = "groups";
    private static final String OFFER_ADD_TO_ORDER = "offerAddToOrder";
    private static final String DELETE_FROM_ORDER = "deleteFromOrder";

    PizzaBot (String inDirectory, String outDirectory) {
        this.inDirectory = Paths.get(inDirectory);
        this.outDirectory = Paths.get(outDirectory);
    }

    // сохраняем количество позиций в заказе
    int getOrderKey(Integer userId) {
        Integer orderKey = (Integer) getUserData(userId, ORDER_KEY);
        Integer count = 0;
        if (orderKey != null)
            count = orderKey;
        ++count;
        return count;
    }

    void setOrderInfo(Integer userId, FoundTags tags, Integer count) {
        if (count == 1) {
            setUserData(userId, ORDER_KEY, count);
            setUserData(userId, ORDER + count, getNameFound(tags));
            setUserData(userId, QUANTITY + count, 1);
            List<Integer> prices = new ArrayList<>();
            prices.add(Integer.parseInt(getPriceCashMap()));
            setUserData(userId, PRICES, prices);
            setUserData(userId, DELETE_FROM_ORDER, "no");
        } else {
            boolean noDuplicates = true;
            Integer orderKey = (Integer) getUserData(userId, ORDER_KEY);
            for (int i = 1; i <= orderKey; i++) {
                if (getNameFound(tags).equals(getUserData(userId, ORDER + i))) {
                    Integer quantity = (Integer) getUserData(userId, QUANTITY + i);
                    setUserData(userId, QUANTITY + i, ++quantity);
                    List<Integer> prices = (List<Integer>) getUserData(userId, PRICES);
                    Integer price = prices.get(i - 1);
                    price += Integer.parseInt(getPriceCashMap());
                    prices.set(i - 1, price);
                    noDuplicates = false;
                    break;
                }
            }
            if (noDuplicates) {
                setUserData(userId, ORDER_KEY, count);
                setUserData(userId, ORDER + count, getNameFound(tags));
                setUserData(userId, QUANTITY + count, 1);
                List<Integer> prices = (List<Integer>) getUserData(userId, PRICES);
                prices.add(Integer.parseInt(getPriceCashMap()));
            }
        }
    }

    // сохраняем позиции заказа и формируем множество из групп заказанных продуктов, если foundCount(tags) > 1
    String saveOrderItem(Integer userId, FoundTags tags) {
        Integer count = getOrderKey(userId);
        setOrderInfo(userId, tags, count);
        if (getUserData(userId, GROUPS) == null) {
            Set<String> groups = new HashSet<>();
            groups.add(getGroupCashMap());
            setUserData(userId, GROUPS, groups);
            /* предлагаем заказать дополнительные группы товаров к товарам, присутствующим в заказе
             * (если товары, присутствующие в заказе подразумевают дололнительные товары) */
            TreeMultimap<String, String> foundAdditionalGroups = getMapFoundAdditionalGroups(getAllAdditionalGroups(), groups, getGroupCashMap());
                if (!foundAdditionalGroups.isEmpty())
                    return "Отлично! Добавил в твой заказ.\n" + suggestAdditionalGroups(foundAdditionalGroups);
        } else {
            Set<String> groups = (Set<String>) getUserData(userId, GROUPS);
            groups.add(getGroupCashMap());
            TreeMultimap<String, String> foundAdditionalGroups = getMapFoundAdditionalGroups(getAllAdditionalGroups(), groups, getGroupCashMap());
            if (!foundAdditionalGroups.isEmpty())
                return "Отлично! Добавил в твой заказ.\n" + suggestAdditionalGroups(foundAdditionalGroups);
        }
        return "Отлично! Добавил в твой заказ.\nДружище, может что-то ещё?\n" + MENU;
    }

    String finishCheck(Integer userId, String text) {
        // если в заказе ничего нет, то метод вернёт null
        if (getUserData(userId, ORDER_KEY) == null)
            return "Дружище, ты ещё ничего не заказал((\n" + MENU;
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
        if (getUserData(userId, ADDRESS_KEY) == null) {
            setUserData(userId, ADDRESS_KEY, "*");
            return "Дружище, пиши свой адрес";
        }
        setUserData(userId, ADDRESS_KEY, text);
        String order = "Твой заказ: " + getFullOrder(userId) + "\n";
        String orderPrice = "Сумма заказа: " + getOrderPrice(userId) + "\n";
        String address = "Адрес доставки: " + getUserData(userId, ADDRESS_KEY) + "\n";
        String contentFile = getFullOrder(userId) + "\n" + getOrderPrice(userId) + "\n" + getUserData(userId, ADDRESS_KEY);
        createFileOrder(userId, outDirectory, contentFile);
        return "Итого:\n" + order + orderPrice + address + "Спасибо за заказ, дружище!";
    }

    String deleteFromOrder(Integer userId, FoundTags tags) {
        if (getUserData(userId, DELETE_FROM_ORDER).equals("yes"))  {
            boolean deletedNotFound = searchDeletedProduct(userId, tags);
            if (deletedNotFound)
                return "Дружище, ты что-то напутал. Такого нет в твоём заказе((";
            return "Дружище, \"" + getNameFound(tags) + "\" успешно удалён из твоего заказа.\nМожет что-то ещё?\n" + MENU;
        } else {
            setUserData(userId, DELETE_FROM_ORDER, "yes");
            return "Дружище, если ты желаешь что-то удалить из своего заказа," +
                    " то напиши полное название удаляемого товара, если передумал, то напиши \"передумал\"";
        }
    }

    boolean searchDeletedProduct(Integer userId, FoundTags tags) {
        boolean deletedNotFound = true;
        Integer count = (Integer) getUserData(userId, ORDER_KEY);
        for (int i = 1; i <= count ; i++) {
            if (getNameFound(tags).equals(getUserData(userId, ORDER + i))) {
                if ((Integer) getUserData(userId, QUANTITY + i) > 1) {
                    Integer quantity = (Integer) getUserData(userId, QUANTITY + i);
                    setUserData(userId, QUANTITY + i, --quantity);
                    List<Integer> prices = (List<Integer>) getUserData(userId, PRICES);
                    Integer price = prices.get(i - 1);
                    price -= Integer.parseInt(getPriceCashMap());
                    prices.set(i - 1, price);
                } else {
                    Integer orderKey = (Integer) getUserData(userId, ORDER_KEY);
                    if (orderKey > 1)
                        setUserData(userId, ORDER_KEY, --orderKey);
                    else
                        deleteFromUserData(userId, ORDER_KEY);
                    deleteFromUserData(userId, ORDER + i);
                    deleteFromUserData(userId, QUANTITY + i);
                    List<Integer> prices = (List<Integer>) getUserData(userId, PRICES);
                    prices.remove(i - 1);
                }
                setUserData(userId, DELETE_FROM_ORDER, "no");
                deletedNotFound = false;
                break;
            }
        }
        return deletedNotFound;
    }

    String cancelDeleteFromOrder(Integer userId) {
        setUserData(userId, DELETE_FROM_ORDER, "no");
        return "Дружище, продолжай заказывать.\n" + MENU;
    }

    // формируем список основных товаров, имеющихся в заказе и список дополнительных к ним товаров
    String suggestAdditionalGroups(TreeMultimap<String, String> map) {
        Set<Map.Entry<String, String>> entries = map.entries();
        Set<String> result1 = new HashSet<>();
        Set<String> result2 = new HashSet<>();
        for (Map.Entry<String, String> entry : entries) {
            result1.add(entry.getKey());
            result2.add(entry.getValue());
        }
        return "Дружище, в твоём заказе присутствуют(ет):\n" + outputColumn(result1) +
                "Дополнительно к этому предлагаю заказать:\n" + outputColumn(result2) +
                "Если что-то из этого желаешь, то пиши что именно хочешь дозаказать, если " +
                "нет, то может что-то ещё?\n" + MENU;
    }

    // получить Map<String, String> из найденных дополнительных групп товаров к товарам в заказе
    TreeMultimap<String, String> getMapFoundAdditionalGroups(TreeMultimap<String, String> map, Set<String> set, String lastGroup) {
        TreeMultimap<String, String> mapResult = TreeMultimap.create(map);
        Set<Map.Entry<String, String>> entries = mapResult.entries();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            /* например: картошка|соус
             * если значение (соус) содержится в заказанных группах товаров, то удаляем entry
             * или если ключ (картошка) не содержится в заказанных группах товаров - тоже удаляем entry */
            if (set.contains(entry.getValue()) || !set.contains(entry.getKey()) || !entry.getKey().equals(lastGroup)) {
                iterator.remove();
            }
        }

        return mapResult;
    }

    // создаём файл с именем userId и записываем туда данные заказа
    void createFileOrder(Integer userId, Path outDirectory, String content) {
        try {
            Path newFile = outDirectory.resolve(Paths.get(userId.toString() + ".csv"));
            if (Files.notExists(newFile))
                Files.createFile(newFile);
            Files.writeString(newFile, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // вывести заказ на экран
    String getFullOrder(Integer userId) {
        // если в заказе ничего нет, то метод вернёт null
        if (getUserData(userId, ORDER_KEY) == null)
            return null;
        Integer count = (Integer) getUserData(userId, ORDER_KEY);
        String fullOrder = "";
        for (int i = 1; i <= count; i++) {
            if (i == count)
                fullOrder += getUserData(userId, ORDER +  i) + " " +
                        getUserData(userId, QUANTITY + i) + " - " +
                        ((List<Integer>) getUserData(userId, PRICES)).get(i - 1);
            else
                fullOrder += getUserData(userId, ORDER + i) + " " +
                        getUserData(userId, QUANTITY + i) + " - " +
                        ((List<Integer>) getUserData(userId, PRICES)).get(i - 1) + ", ";
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
            clearUserData(userId);

        FoundTags tags = checkTags(text);

        // если текст пользователя содержит цифру, то значит он ввёл свой адрес
        if (containsDigit(text))
            return finishCheck(userId, text);

        // если tags.tags содержит 1 элемент
        if (foundCount(tags) == 1) {
            if (checkLastFound(tags, "привет"))
                return "Привет, дружище! Что желаешь?\n" + MENU;
            if (checkLastFound(tags, "меню"))
                return getMenu() + "Выбери что-то одно, напиши полное название и я добавлю это в твой заказ";
            if (checkLastFound(tags, "заказ")) {
                if (getFullOrder(userId) == null)
                    return "Дружище, ты ещё ничего не заказал((\n" + MENU;
                return "Дружище, вот твой заказ:\n" + getFullOrder(userId);
            }
            if (checkLastFound(tags, "конец"))
                return finishCheck(userId, text);
            if (checkLastFound(tags, "удалить"))
                return deleteFromOrder(userId, tags);
            if (checkLastFound(tags, "передумал"))
                return cancelDeleteFromOrder(userId);
            if (containsWeightEqualTen(tags)) {
                if (getUserData(userId, DELETE_FROM_ORDER) != null && getUserData(userId, DELETE_FROM_ORDER).equals("yes"))
                    return deleteFromOrder(userId, tags);
                return saveOrderItem(userId, tags);
            }
            return "Дружище, под твой запрос подходит:\n" + extract(tags)
                    + "Выбери что-то одно, напиши полное название и я добавлю это в твой заказ";
        }
        if (foundCount(tags) > 1) {
            if (containsWeightEqualTen(tags)) {
                if (getUserData(userId, DELETE_FROM_ORDER) != null && getUserData(userId, DELETE_FROM_ORDER).equals("yes"))
                    return deleteFromOrder(userId, tags);
                return saveOrderItem(userId, tags);
            }
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

        PizzaBot bot = new PizzaBot("C:\\Users\\Эльдорадо\\Desktop\\TestJava\\pizzaBot\\inFolder",
                                    "C:\\Users\\Эльдорадо\\Desktop\\TestJava\\pizzaBot\\outFolder");
        bot.username = "PizzaTastyBot";
        bot.token = "695537327:AAHsUdJEcqZ1ZAnQWEb_bqwr8BdD5A2OwJk";

        bot.addTags(bot.inDirectory);

//        bot.start();
        bot.test();
    }
}
