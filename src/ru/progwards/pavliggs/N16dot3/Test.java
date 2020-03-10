package ru.progwards.pavliggs.N16dot3;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Test {
    ZonedDateTime parseZDT(String str) {
        Locale locale = new Locale("en", "EN");
        ZoneId zid = ZoneId.of("Europe/Moscow");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss.SSS Z zzzz").withLocale(locale).withZone(zid);
        ZonedDateTime zdt = ZonedDateTime.parse(str, dtf);
        return zdt;
    }

    public static void main(String[] args) {
        ZonedDateTime zdt = ZonedDateTime.now();
        System.out.println(zdt);
        System.out.println(new Test().parseZDT("01.01.2020 16:27:14.444 +0300 Moscow Standard Time"));
    }
}