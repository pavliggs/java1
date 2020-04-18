package ru.progwards.java1.project_telegram_bot;

import com.google.common.collect.TreeMultimap;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TelegramBot extends TelegramLongPollingBot {
    public String username;
    public String token;
    private List<Association> associations = new ArrayList();
    private Set<String> allMainGroups = new HashSet<>();
    private Set<String> allAdditionalGroups = new HashSet<>();
    private ConcurrentHashMap<Integer, ConcurrentHashMap<String, Object>> userData;
    private Map<Integer, String> cashMap = new HashMap<>();

    public TelegramBot() {
        ApiContextInitializer.init();
        userData = new ConcurrentHashMap();
    }

    public void addTags(String name, String tags, String group) {
        associations.add(new Association(name, tags, group));
    }

    public void addTags(String name, String tags) {
        associations.add(new Association(name, tags, null));
    }

    // добавляем во множество группы продуктов
    public String addGroup(String group) {
        StringTokenizer tokenizer = new StringTokenizer(group);
        if (tokenizer.countTokens() == 1)
            allMainGroups.add(group);
        else
            allAdditionalGroups.add(group);
        return group;
    }

    public FoundTags checkTags(String text) {
        return findAssociation(text);
    }

    public boolean checkLastFound(FoundTags found, String text) {
        return getNameLastFound(found).toLowerCase().equals(text.toLowerCase());
    }

    public String getNameLastFound(FoundTags found) {
        Object[] a = found.tags.values().toArray();
        return a.length > 0 ? getSubString((String) a[a.length - 1], "-", 0) : "";
    }

    public String getGroupLastFound(FoundTags found) {
        Object[] a = found.tags.values().toArray();
        return a.length > 0 ? getSubString((String) a[a.length - 1], "-", 1) : "";
    }

    // получить имя продукта, когда пользователь ввёл полное имя продукта (weight = 10)
    public String getNameFound(FoundTags found) {
        Set<Map.Entry<Integer, String>> set = found.tags.entries();
        String name = "";
        for (Map.Entry<Integer, String> entry : set) {
            if (entry.getKey() == 10) {
                cashMap.put(entry.getKey(), entry.getValue());
                name = getSubString(entry.getValue(), "-", 0);
                break;
            }
        }
        return name;
    }

    public String getSubString(String str, String delimiter, int i) {
        String[] arr = str.split(delimiter);
        return arr[i];
    }

    public int foundCount(FoundTags found) {
        return found.tags.size();
    }

    public boolean containsWeightEqualTen(FoundTags found) {
        return found.tags.containsKey(10);
    }

    public String extract(FoundTags found) {
            Collection<String> names = found.tags.values();

            String res = "";

            for (String name : names)
                res += getSubString(name, "-", 0) + "\n";

            return res;
    }

    private int findAssociation(Association ass, String text) {
        int weight = 0;
        if (text.toLowerCase().contains(ass.name.toLowerCase())) {
            weight += 10;
        } else {
            String[] tags = ass.tags.split(",");
            String[] var8 = tags;
            int var7 = tags.length;

            for(int var6 = 0; var6 < var7; ++var6) {
                String s = var8[var6];
                if (text.toLowerCase().contains(s.trim().toLowerCase())) {
                    ++weight;
                }
            }
        }

        return weight;
    }

    public FoundTags findAssociation(String text) {
        TreeMultimap<Integer, String> result = TreeMultimap.create();
        Iterator var4 = associations.iterator();

        while(var4.hasNext()) {
            Association ass = (Association)var4.next();
            int weight = findAssociation(ass, text);
            if (weight > 0) {
                result.put(weight, ass.name + "-" + ass.group);
            }
        }

        return new FoundTags(result);
    }

    public void setUserData(Integer userid, String name, Object data) {
        ConcurrentHashMap thisData;
        if (userData.containsKey(userid)) {
            thisData = userData.get(userid);
        } else {
            thisData = new ConcurrentHashMap();
            userData.put(userid, thisData);
        }

        thisData.put(name, data);
    }

    public Object getUserData(Integer userid, String name) {
        return userData.containsKey(userid) ? userData.get(userid).get(name) : null;
    }

    public void cleartUserData(Integer userid) {
        if (userData.containsKey(userid)) {
            userData.remove(userid);
        }

    }

    public String getBotToken() {
        return token;
    }

    public String getBotUsername() {
        return username;
    }

    // получить множество из основных групп товаров
    public Set<String> getAllMainGroups() {
        return allMainGroups;
    }

    // получить множество из дополнительных групп товаров
    public Set<String> getAllAdditionalGroups() {
        return allAdditionalGroups;
    }

    // получить имя группы из cashMap
    public String getGroupCashMap() {
        return getSubString(cashMap.get(10), "-", 1);
    }

    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage() && update.getMessage().hasText()) {
                Message inMessage = update.getMessage();
                String text = inMessage.getText();
                SendMessage outMessage = new SendMessage();
                outMessage.setChatId(inMessage.getChatId());
                outMessage.setText(this.processMessage(inMessage.getFrom().getId(), text));
                this.execute(outMessage);
            }
        } catch (TelegramApiException var5) {
            var5.printStackTrace();
        }
    }

    public String processMessage(Integer userid, String text) {
        return text;
    }

    public void start() {
        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(this);
        } catch (TelegramApiRequestException var3) {
            var3.printStackTrace();
        }

    }

    static class Association {
        private String name;
        private String tags;
        private String group;

        public Association(String name, String tags, String group) {
            this.name = name;
            this.tags = tags;
            this.group = group;
        }
    }

    public static class FoundTags {
        private TreeMultimap<Integer, String> tags;

        public FoundTags(TreeMultimap<Integer, String> tags) {
            this.tags = tags;
        }
    }
}
