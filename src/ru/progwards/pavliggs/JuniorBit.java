package ru.progwards.pavliggs;

public class JuniorBit {

    //получить младший бит двоичного числа
    public static int getBit(byte value) {
        int result = value & 0b1;
        return result;
    }

    enum Grade {
        VERYBAD,
        BAD,
        SATISFACTORILY,
        GOOD,
        EXCELLENT,
        NOTDEFINED,
    }

    static Grade intToGrade(int grade) {
        switch (grade) {
            case 1: return Grade.VERYBAD;
            case 2: return Grade.BAD;
            case 3: return Grade.SATISFACTORILY;
            case 4: return Grade.GOOD;
            case 5: return Grade.EXCELLENT;
            default: return Grade.NOTDEFINED;
        }
    }

    public static void main(String[] args) {
        System.out.println(getBit((byte) 0b1000));
        System.out.println(intToGrade(4));
    }
}
