package ru.progwards.java2.lessons.basetypes;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class BiDirList<T> implements Iterable<T> {

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int indx = 0;
            private ListItem<T> lastReturned;
            private ListItem<T> current = head;

            @Override
            public boolean hasNext() {
                return indx < size();
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException();

                lastReturned = current;
                current = current.getNext();
                indx++;
                return lastReturned.getItem();
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        ListItem<T> current = head;

        while (current != null) {
            action.accept(current.getItem());
            current = current.getNext();
        }
    }

    static class ListItem<T> {

        private T item;
        private ListItem<T> next;
        private ListItem<T> prev;
        private int indx;

        ListItem(T item) {
            this.item = item;
        }

        void setPrev(ListItem<T> item) {
            prev = item;
        }

        void setNext(ListItem<T> item) {
            next = item;
        }

        void setIndx(int num) {
            indx = num;
        }

        T getItem() {
            return item;
        }

        ListItem<T> getNext() {
            return next;
        }

        ListItem<T> getPrev() {
            return prev;
        }

        int getIndx() {
            return indx;
        }
    }

    private ListItem<T> head;
    private ListItem<T> tail;
    private int size = 0;
    private int count = 0;

    ListItem<T> getHead() {
        return head;
    }

    ListItem<T> getTail() {
        return tail;
    }

    public int size() {
        return size;
    }

    public void addLast(T item) {
        ListItem<T> t = tail;
        ListItem<T> newListItem = new ListItem<>(item);
        newListItem.setIndx(count++);
        tail = newListItem;
        if (t == null) {
            head = newListItem;
        } else {
            t.setNext(tail);
            tail.setPrev(t);
        }
        ++size;
    }

    public void addFirst(T item) {
        ListItem<T> h = head;
        ListItem<T> newListItem = new ListItem<>(item);
        newListItem.setIndx(0);
        head = newListItem;
        if (h == null) {
            tail = newListItem;
        } else {
            head.setNext(h);
            h.setPrev(head);
            increaseIndx(h, true);
        }
        ++size;
    }

    public T at(int i) {
        ListItem<T> current = head;
        T result = null;

        while (current != null) {
            if (current.getIndx() == i) {
                result = current.getItem();
                break;
            }
            current = current.getNext();
        }

        return result;
    }

    public void remove(T item) {
        ListItem<T> current = head;

        while (current != null) {
            if (current.getItem().equals(item)) {
                if (current.equals(head))
                    head = current.getNext();
                else
                    current.getPrev().setNext(current.getNext());
                if (current.equals(tail))
                    tail = current.getPrev();
                else
                    current.getNext().setPrev(current.getPrev());
                // уменьшаем индексы элементов на 1
                increaseIndx(current.getNext(), false);
                // уменьшаем размер списка
                --size;
                return;
            }
            current = current.getNext();
        }
    }

    public static<T> BiDirList<T> from(T[] array) {
        BiDirList<T> list = new BiDirList<>();
        addToList(list, array);
        return list;
    }

    public static<T> BiDirList<T> of(T...array) {
        return from(array);
    }

    public void toArray(T[] array) {
        int i = 0;
        ListItem<T> current = head;

        while (current != null) {
            array[i++] = current.getItem();
            current = current.getNext();
        }
    }

    // метод создания элементов списка из элементов массива
    private static<T> void addToList(BiDirList<T> list, T[] array) {
        for (int i = 0; i < array.length; i++) {
            ListItem<T> t = list.tail;
            ListItem<T> newListItem = new ListItem<>(array[i]);
            newListItem.setIndx(list.count++);
            list.tail = newListItem;
            if (t == null) {
                list.head = newListItem;
            } else {
                t.setNext(list.tail);
                list.tail.setPrev(t);
            }
        }
        list.size = array.length;
    }

    // метод увеличивает или уменьшает на 1 индексы элементов, начиная с элемента current
    private void increaseIndx(ListItem<T> current, boolean isIncrement) {
        while (current != null) {
            int currentIndx = current.getIndx();
            // если true, то увеличиваем индексы
            if (isIncrement)
                current.setIndx(++currentIndx);
            else
                current.setIndx(--currentIndx);
            current = current.getNext();
        }
    }
}
