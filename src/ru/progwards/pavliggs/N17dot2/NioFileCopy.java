package ru.progwards.pavliggs.N17dot2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class NioFileCopy {
    final static String HOME_DIR = "C:\\Users\\Эльдорадо\\IdeaProjects\\HelloWorld";

    public static void main(String[] args) {
        Path dir = Paths.get(HOME_DIR);

        try {
            Path srcFile = dir.resolve("wiki.test.tokens");
            Path dstFile = dir.resolve("wiki.test.copy.tokens");

            // бросается исключение, если файл "wiki.test.copy.tokens" будет создан до выполнения программы
//            Files.copy(srcFile, dstFile);

            // при выполнении команды файл будет удаляться и создаваться заново (исключения не будет)
            Files.copy(srcFile, dstFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
