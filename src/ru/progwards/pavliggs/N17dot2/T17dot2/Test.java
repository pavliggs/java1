package ru.progwards.pavliggs.N17dot2.T17dot2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test {
    boolean replaceF(String name) {
        try {
            Path path = Paths.get(name);
            byte[] allBytes = Files.readAllBytes(path);
            for (int i = 0; i < allBytes.length; i++) {
                if (allBytes[i] == 'f')
                    allBytes[i] = 'F';
            }
            Files.write(path, allBytes);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Test().replaceF("changeFtof.txt"));
        System.out.println((byte)'F');
        System.out.println((byte)'f');
    }
}
