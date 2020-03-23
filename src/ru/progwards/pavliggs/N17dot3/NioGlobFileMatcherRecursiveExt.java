package ru.progwards.pavliggs.N17dot3;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class NioGlobFileMatcherRecursiveExt {
    private final static String pattern = "glob:**/*.java";
    private final static Path dir = Paths.get("");

    public static void main(String[] args) throws IOException {
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(pattern);

        Files.walkFileTree(dir, new SimpleFileVisitor<>() {
           @Override
           public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
               if (pathMatcher.matches(path))
                   System.out.println(path.toAbsolutePath());
               return FileVisitResult.CONTINUE;
           }
        });
    }
}
