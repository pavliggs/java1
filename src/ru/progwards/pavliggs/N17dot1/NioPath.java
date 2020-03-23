package ru.progwards.pavliggs.N17dot1;

import java.nio.file.Path;
import java.nio.file.Paths;

public class NioPath {
    public static void main(String[] args) {
        Path path = Paths.get("C:\\Users\\Эльдорадо\\IdeaProjects\\ffscfsgv");
        System.out.println(path.toAbsolutePath());
    }
}
