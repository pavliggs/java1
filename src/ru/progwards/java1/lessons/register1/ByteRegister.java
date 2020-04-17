package ru.progwards.java1.lessons.register1;

import java.util.Arrays;

public class ByteRegister {
    private byte value;
    byte[] bytes;

    public ByteRegister() {
        bytes = new byte[8];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = Byte.parseByte(new Bit().toString());
        }
    }

    public ByteRegister(byte value) {
        bytes = new byte[8];
        for (int i = bytes.length - 1; i >= 0; i--) {
            bytes[i] = (byte)(value & 0b1);
            value >>= 1;
        }
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < bytes.length; i++) {
            res += bytes[i];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new ByteRegister());
        System.out.println(Arrays.toString(new ByteRegister((byte)127).bytes));
    }
}
