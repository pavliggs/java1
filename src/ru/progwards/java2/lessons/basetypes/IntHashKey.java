package ru.progwards.java2.lessons.basetypes;

import java.util.Objects;

public class IntHashKey implements HashValue {
    private int value;

    IntHashKey(int value) {
        this.value = value;
    }

    @Override
    public int getHash() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntHashKey that = (IntHashKey) o;
        return value == that.value;
    }
}
