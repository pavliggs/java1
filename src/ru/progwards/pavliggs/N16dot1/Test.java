package ru.progwards.pavliggs.N16dot1;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Test {
    Date createDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(1986, 1, 28, 0, 0, 0);
        Date date = new Date(calendar.getTimeInMillis());
        return date;
    }

    public static void main(String[] args) {
        System.out.println(new Test().createDate());
    }
}
