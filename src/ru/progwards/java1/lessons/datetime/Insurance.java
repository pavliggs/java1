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
            start = ZonedDateTime.of(ld, LocalTime.of(0, 0), ZoneId.systemDefault());
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
        ZonedDateTime zdt = ZonedDateTime.from(start);
        System.out.println(zdt);
        zdt = zdt.plusMonths(months);
        zdt = zdt.plusDays(days);
        zdt = zdt.plusHours(hours);
        duration = Duration.between(start, zdt);
    }

    public void setDuration(String strDuration, FormatStyle style) {
        if (style == FormatStyle.SHORT)
            duration = Duration.ofMillis(Long.parseLong(strDuration));
        if (style == FormatStyle.LONG) {
            DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDateTime ldt = LocalDateTime.parse(strDuration, dtf);
            ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
            ZonedDateTime finish = ZonedDateTime.from(start);
            finish = finish.plusYears(zdt.getYear());
            finish = finish.plusMonths(zdt.getMonthValue());
            finish = finish.plusDays(zdt.getDayOfMonth());
            finish = finish.plusHours(zdt.getHour());
            finish = finish.plusMinutes(zdt.getMinute());
            finish = finish.plusSeconds(zdt.getSecond());
            duration = Duration.between(start, finish);
        }
        if (style == FormatStyle.FULL)
            duration = Duration.parse(strDuration);
    }

    public boolean checkValid(ZonedDateTime dateTime) {
        if (duration == null && dateTime.isAfter(start))
            return true;
        if (duration == null && dateTime.isBefore(start))
            return false;
        ZonedDateTime finish = (ZonedDateTime)duration.addTo(start);
        if (dateTime.isAfter(start) && dateTime.isBefore(finish))
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
//        ZonedDateTime zdtStart = ZonedDateTime.of(2019, 6, 2, 10, 0, 0, 0, ZoneId.systemDefault());
//        Insurance ins = new Insurance("2007-12-03", FormatStyle.SHORT);
//        ZonedDateTime zdtFinish = ZonedDateTime.of(2020, 3, 9, 10, 0, 0, 0, ZoneId.systemDefault());
//        ins.setDuration(zdtFinish);
//        System.out.println(ins.duration);
//        ZonedDateTime zdt = ZonedDateTime.of(2008, 3, 11, 20, 30, 0, 0, ZoneId.systemDefault());
//        System.out.println(ins.checkValid(zdt));
//        System.out.println(ins);
        Insurance ins2 = new Insurance("2020-02-11T00:22:12.537742+03:00[Europe/Moscow]", FormatStyle.FULL);
        ins2.setDuration("0000-01-01T00:00:00", Insurance.FormatStyle.LONG);
        System.out.println(ins2.duration);
        System.out.println(ins2);

        Insurance ins3 = new Insurance("2020-03-12T00:22:12.513631+03:00[Europe/Moscow]", FormatStyle.FULL);
        ins3.setDuration("1", Insurance.FormatStyle.SHORT);
        System.out.println(ins3);
    }
}
