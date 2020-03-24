package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class FilesSelect {
    public void selectFiles(String inFolder, String outFolder, List<String> keys) {
        // задаём фильтр для файлов, которые будем искать (ищем только файлы с расширением txt)
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/*.txt");

        try {
            Files.walkFileTree(Paths.get(inFolder), new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    try {
                        if (pathMatcher.matches(path)) {
                            // записываем содержимое файла в переменную content
                            String content = Files.readString(path);
                            for (int i = 0; i < keys.size(); i++) {
                                // если content содержит подстроку равную элементу списка
                                if (content.contains(keys.get(i))) {
                                    // создаем новый путь, добавляя к outFolder путь с именем, равным элементу списка
                                    Path newFolder = Paths.get(outFolder).resolve(keys.get(i));
                                    // если такой папки нет, то создаём её
                                    if (Files.notExists(newFolder))
                                        // создаём папку на основании пути newFolder
                                        Files.createDirectory(newFolder);
                                    /* создаем новый путь, добавляя к newFolder путь с именем, равным имени файла,
                                     * путь которого передаётся в метод visitFile */
                                    Path newFile = newFolder.resolve(path.getFileName());
                                    // создаём файл на основании пути newFile
                                    Files.createFile(newFile);
                                    // записываем в новый файл содержимое переменной content
                                    Files.writeString(newFile, content);
                                }
                            }
                        }
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path path, IOException e) {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        new FilesSelect().selectFiles("C:\\Users\\Эльдорадо\\inFolder", "C:\\Users\\Эльдорадо\\outFolder",
                List.of("cat", "dog", "crocodile", "fox", "wolf"));
    }
}
