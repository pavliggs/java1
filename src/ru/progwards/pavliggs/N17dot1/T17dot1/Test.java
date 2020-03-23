package ru.progwards.pavliggs.N17dot1.T17dot1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test {
    String createFolder(String name) {
        Path pathCurrent = Paths.get("");
        Path pathNew = pathCurrent.resolve(name);
        File file = new File(pathNew.toAbsolutePath().toString());
        file.mkdir();
        return pathCurrent.toAbsolutePath().getParent().toString();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new Test().createFolder("directoryJava"));

        Path pathName = Paths.get("symlink");
        System.out.println(Files.isSymbolicLink(pathName));
        System.out.println(pathName.toRealPath());
    }
}
