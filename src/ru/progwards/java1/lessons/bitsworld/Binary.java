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
//        if (num < 0) {
////            num += 128;
////            return 1 + String.format("%7s", Integer.toBinaryString(num)).replace(' ', '0');
////        }
////
////        else
////            return String.format("%8s", Integer.toBinaryString(num)).replace(' ', '0');
        //вариант с использованием битовых операций!!!
        String result = "";
        /*
        при помощи цикла и сдвига числа вправо получаем последовательно все младшие биты числа
        и складываем их в одну строку
        */
        for (int i = 0; i < 8; i++) {
            int resultInt = num & 0b1;
            result = resultInt + result;
            num >>= 1;
        }
        return result;
    }

    public static void main(String[] args) {
        Binary x = new Binary((byte)-127);
        System.out.println(x);
    }
}
