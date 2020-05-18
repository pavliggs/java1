package ru.progwards.pavliggs.java2.L3;

public class MyComparable<T extends Comparable<T>> {

    public T max(T... items) {
        T tmp = items[0];

        for (int i = 1; i < items.length; i++) {
            if (tmp.compareTo(items[i]) == -1)
                tmp = items[i];
        }

        return tmp;
    }

    public static void main(String[] args) {
        MyComparable<People> obj = new MyComparable<>();

        People people1 = new People("Jane", 17);
        People people2 = new People("Franky", 20);

        System.out.println(obj.max(people1, people2));
    }
}

class People implements Comparable<People> {

    String name;
    int age;

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "name = " + name + "\n" + "age = " + age;
    }

    @Override
    public int compareTo(People o) {
        return Integer.compare(this.age, o.age);
    }
}
