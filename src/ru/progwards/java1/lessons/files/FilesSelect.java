package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class FilesSelect {
    public void selectFiles(String inFolder, String outFolder, List<String> keys) {
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/*.txt");

        try {
            Files.walkFileTree(Paths.get(inFolder), new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    if (pathMatcher.matches(path)) {
                        String content;
                        try {
                            content = Files.readString(path);
                            for (int i = 0; i < keys.size(); i++) {
                                if (content.contains(keys.get(i))) {
                                    Path newFolder = Paths.get(outFolder).resolve(keys.get(i));
                                    if (Files.notExists(newFolder))
                                        Files.createDirectory(newFolder);
                                    Path newFile = newFolder.resolve(path.getFileName());
                                    Files.createFile(newFile);
                                    Files.writeString(newFile, content);
                                }
                            }
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
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
