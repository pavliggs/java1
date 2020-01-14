package ru.progwards.java1.lessons.bigints;

public class AbsInteger  {
    long a;

    public long getNum() {
        return a;
    }

    static AbsInteger add(AbsInteger num1, AbsInteger num2) {
        long res = num1.getNum() + num2.getNum();
        if (res >= -128 && res <= 127)
            return new ByteInteger((byte)res);
        else if ((res >= -32768 && res <= -129) || (res >= 128 && res <= 32767))
            return new ShortInteger((short)res);
        else if ((res >= -2147483648 && res <= -32769) || (res >= 32768 && res <= 2147483647))
            return new IntInteger((int)res);
        return new AbsInteger();
    }

    public static void main(String[] args) {
        System.out.println(add(new ByteInteger((byte)3), new ByteInteger((byte)4)));
        System.out.println(add(new ShortInteger((short)-10255), new ShortInteger((short)-12698)));
        System.out.println(add(new IntInteger(0), new IntInteger(2147483647)));
    }
}
