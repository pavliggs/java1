package ru.progwards.pavliggs.java2.L3;

// пример обобщенного класса
public class NoneSafeGeneric<T> {

    private T[] items;
    private int count;

    public NoneSafeGeneric() {
        items = (T[])new Object[10];
        count = 0;
    }

    public void add(T item) {
        items[count++] = item;
    }

    public T get(int indx) {
        return items[indx];
    }

    public static void main(String[] args) {
        NoneSafeGeneric<String> obj = new NoneSafeGeneric<>();
        obj.add("Hi");
        obj.add("Hello");

        System.out.println(obj.get(1));
    }
}
