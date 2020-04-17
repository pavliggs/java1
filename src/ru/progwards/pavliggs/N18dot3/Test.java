package ru.progwards.pavliggs.N18dot3;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Test {
    void printPersons(Person[] persons) {
        for (int i = 0; i < persons.length; i++) {
            System.out.format(new Locale("ru", "RU"), "|%-10s|%2$td/%2$tm/%2$tY|%3$,10.2f|\n",
                    persons[i].name, persons[i].birth, persons[i].salary);
        }
    }

    public static void main(String[] args) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.clear();
        calendar1.set(1990, 5, 18, 0, 0, 0);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.clear();
        calendar2.set(1992, 0, 19, 0, 0, 0);
        Person person1 = new Person("Pavel", new Date(calendar1.getTimeInMillis()), 200000.001);
        Person person2 = new Person("Maria", new Date(calendar2.getTimeInMillis()), 55331.123);
        Person[] persons = {person1, person2};

        new Test().printPersons(persons);
    }
}
