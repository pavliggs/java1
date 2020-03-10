package ru.progwards.pavliggs.N16dot2;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Set;

public class JavaTime {
    public static void main(String[] args) {
        Instant instant = Instant.now(); // текущее время
        System.out.println(instant.getEpochSecond()); // секунды с эпохи
        System.out.println(instant.getNano()); // не совсем понятно почему такое число
        System.out.println(instant);
        System.out.println(instant.minus(15, ChronoUnit.MINUTES)); // убавили 15 минут
        System.out.println(instant.plus(1, ChronoUnit.DAYS)); // прибавили 1 день

        ZoneId zid1 = ZoneId.of("Europe/Moscow");
        System.out.println(zid1.getRules().getOffset(Instant.now()));
        System.out.println(zid1.getRules().isDaylightSavings(Instant.now()));

        Set<String> zones = ZoneId.getAvailableZoneIds(); // множество из зон
        System.out.println(zones);

        ZonedDateTime zdt = ZonedDateTime.now(); // по умолчанию
        System.out.println(zdt);
        DayOfWeek dow = zdt.getDayOfWeek();
        System.out.println(dow);

        ZonedDateTime zdt1 = ZonedDateTime.now(ZoneId.of("Africa/Cairo")); // вручную указываем зону
        System.out.println(zdt1);

        OffsetDateTime odt = OffsetDateTime.now(ZoneId.of("Pacific/Kwajalein"));
        System.out.println(odt);

        LocalDateTime ldt1 = LocalDateTime.now();
        LocalDateTime ldt2 = ldt1.plusDays(4);
        Duration duration = Duration.between(ldt1, ldt2);
        System.out.println(duration.toHours());
    }
}
