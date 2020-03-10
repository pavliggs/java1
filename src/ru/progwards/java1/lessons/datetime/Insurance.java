package ru.progwards.java1.lessons.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Insurance {
    private ZonedDateTime start;
    private Duration duration;

    public static enum FormatStyle {
        SHORT,
        LONG,
        FULL,
    }

    public Insurance(ZonedDateTime start) {
        this.start = start;
    }

    public Insurance(String strStart, FormatStyle style) {
        if (style == FormatStyle.SHORT) {
            LocalDate ld = LocalDate.parse(strStart, DateTimeFormatter.ISO_LOCAL_DATE);
            start = ZonedDateTime.of(ld, LocalTime.now(), ZoneId.systemDefault());
        }
        if (style == FormatStyle.LONG) {
            LocalDateTime ldt = LocalDateTime.parse(strStart, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            start = ZonedDateTime.of(ldt, ZoneId.systemDefault());
        }
        if (style == FormatStyle.FULL)
            start = ZonedDateTime.parse(strStart, DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public void setDuration(ZonedDateTime expiration) {
        duration = Duration.between(start, expiration);
    }

    public void setDuration(int months, int days, int hours) {
        ZonedDateTime zdt = start;
        zdt.plusMonths(months);
        zdt.plusDays(days);
        zdt.plusHours(hours);
        duration = Duration.between(start, zdt);
    }

    public void setDuration(String strDuration, FormatStyle style) {
        if (style == FormatStyle.SHORT)
            duration = Duration.ofMillis(Long.parseLong(strDuration));
        if (style == FormatStyle.LONG) {
            DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDateTime ldt = LocalDateTime.parse(strDuration, dtf);
            ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
            start.plusYears(zdt.getYear());
            start.plusMonths(zdt.getMonthValue());
            start.plusDays(zdt.getDayOfMonth());
            start.plusHours(zdt.getHour());
            start.plusMinutes(zdt.getMinute());
            start.plusSeconds(zdt.getSecond());
            duration = Duration.between(start, zdt);
        }
        if (style == FormatStyle.FULL)
            duration = Duration.parse(strDuration);
    }

    public boolean checkValid(ZonedDateTime dateTime) {
        if (duration == null)
            return true;
        ZonedDateTime finish = (ZonedDateTime)duration.addTo(start);
        if (dateTime.isBefore(finish))
            return true;
        return false;
    }

    @Override
    public String toString() {
        String validStr;
        if (checkValid(ZonedDateTime.now()))
            validStr = "is valid";
        else
            validStr = "is not valid";
        return "Insurance issued on " + start + " " + validStr;
    }

    public static void main(String[] args) {
        ZonedDateTime zdtStart = ZonedDateTime.of(2019, 6, 2, 10, 0, 0, 0, ZoneId.systemDefault());
        Insurance ins = new Insurance("2007-12-03T10:15:30+01:00[Europe/Paris]", FormatStyle.FULL);
        ZonedDateTime zdtFinish = ZonedDateTime.of(2020, 3, 9, 10, 0, 0, 0, ZoneId.systemDefault());
        ins.setDuration(zdtFinish);
        System.out.println(ins.duration);
        ZonedDateTime zdt = ZonedDateTime.of(2008, 3, 11, 20, 30, 0, 0, ZoneId.systemDefault());
        System.out.println(ins.checkValid(zdt));
        System.out.println(ins);
    }
}
