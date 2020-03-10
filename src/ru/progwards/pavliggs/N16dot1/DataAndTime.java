package ru.progwards.pavliggs.N16dot1;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class DataAndTime {
    public static void main(String[] args) {
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Moscow");
        System.out.println(timeZone);

        Locale locale = new Locale("ru", "RU");
        System.out.println(timeZone.getDisplayName(false, TimeZone.LONG, locale));

        String[] zones = TimeZone.getAvailableIDs(); // массив из наименований часовых поясов
        System.out.println(Arrays.toString(zones));

        Calendar calendar = Calendar.getInstance(timeZone, locale);
        System.out.println(calendar.getTime()); // получить Date
        System.out.println(calendar.get(Calendar.MONTH)); // получим поле (месяц в году)
    }
}
