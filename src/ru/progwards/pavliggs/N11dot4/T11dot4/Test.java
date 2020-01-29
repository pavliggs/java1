package ru.progwards.pavliggs.N11dot4.T11dot4;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Test {
    public String setStars(String filename) {
        try(RandomAccessFile randomAccessFile = new RandomAccessFile(filename, "rw")) {
            //читаем строку в файле
            String str = randomAccessFile.readLine();
            if (str == null)
                return null;
            String res = "";
            for (int i = 9; i < str.length(); i += 10) {
                //ставим курсор на определённую позицию
                randomAccessFile.seek(i);
                //читаем символ на этой позиции и записываем его в переменную
                res += (char)randomAccessFile.read();
                //снова ставим курсор на эту позицию, т.к. при чтении символа курсор смещается на одну позицию вперёд
                randomAccessFile.seek(i);
                //перезаписываем эту символ в данной позиции на любой другой символ (в данном примере '*')
                randomAccessFile.write(42);
            }
            return res;
        } catch (FileNotFoundException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        System.out.println(new Test().setStars("RandomAccess.txt"));
    }
}
