package ru.progwards.java1.lessons.bitsworld;

public class Binary {
    byte num;

    public Binary(byte num) {
        this.num = num;
    }

    @Override
    public String toString() {
        //вариант БЕЗ использования битовых операций!!!
        /*в случае отрицательного числа - прибавляем к нему 128 и эти два числа в двоичном представлении
        буду отличаться лишь знаком (то есть первым битом)!
        следовательно прибавляем к выводимому двоичному представлению числа единицу спереди
        */
        if (num < 0) {
            num += 128;
            return 1 + String.format("%7s", Integer.toBinaryString(num)).replace(' ', '0');
        }

        else
            return String.format("%8s", Integer.toBinaryString(num)).replace(' ', '0');
    }

    public static void main(String[] args) {
        Binary x = new Binary((byte)-5);
        System.out.println(x);
    }
}
