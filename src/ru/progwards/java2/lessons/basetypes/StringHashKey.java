package ru.progwards.java2.lessons.basetypes;

public class StringHashKey implements HashValue {
    private static final long UINT_MAX = 4294967295L;

    private String value;

    StringHashKey(String value) {
        this.value = value;
    }

    private long unsignedInt(long num) {
        return num % UINT_MAX;
    }

    @Override
    public int getHash() {
        long b = 378551;
        long a = 63689;
        int hash = 0;
        for (int i = 0; i < value.length(); i++) {
            hash = (int)unsignedInt(hash * a + value.charAt(i));
            a = unsignedInt(a * b);
        }
        return Math.abs(hash);
    }

    @Override
    public String toString() {
        return value;
    }
}
