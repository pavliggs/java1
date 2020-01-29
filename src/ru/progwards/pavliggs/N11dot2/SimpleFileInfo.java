package ru.progwards.pavliggs.N11dot2;

import java.io.File;
import java.util.Date;
import java.util.Scanner;

public class SimpleFileInfo {
    public static void main(String[] args) {
        File fileInfo = new File("C:/Users/Эльдорадо/YandexDisk/Скриншоты/2019-11-21_21-48-14.png");
        System.out.println("getParent: " + fileInfo.getParent());
        System.out.println("getPath: " + fileInfo.getPath());
        System.out.println("getName: " + fileInfo.getName());
        System.out.println("lastModified: " + new Date(fileInfo.lastModified()));

        File f = new File("C:/Users/Эльдорадо/Desktop/TestJava/Paradise/newSpace/ihj/LALA");

        if (f.mkdirs())
            System.out.println("Цепь каталогов создана");
        else
            System.out.println("Цепь каталогов не создана");
    }
}
