package ru.progwards.pavliggs.N16dot2.T16dot2;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class Test {
    Instant createInstant() {
        ZonedDateTime zdt = ZonedDateTime.of(2020, 01, 01, 15, 0, 0, 1, ZoneId.of("Europe/Moscow"));
        return Instant.from(zdt);
    }

    public static void main(String[] args) {
        LocalDateTime ldt1 = LocalDateTime.now();
        LocalDateTime ldt2 = ldt1.plusDays(4);
        Duration duration = Duration.between(ldt1, ldt2);
        System.out.println(duration.toHours());

        LocalDateTime ldt3= LocalDateTime.of(2019, 05, 05, 22, 24);
        System.out.println(ldt3);

        ZoneId zid1 = ZoneId.of("Europe/Moscow");
        System.out.println(zid1.getRules().getOffset(Instant.now()));

        System.out.println(new Test().createInstant());
    }
}
