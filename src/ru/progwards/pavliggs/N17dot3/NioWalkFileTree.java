package ru.progwards.pavliggs.N17dot3;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class NioWalkFileTree {
    public static void main(String[] args) throws IOException {
        Files.walkFileTree(Paths.get(""), new SimpleFileVisitor<Path>() {
           @Override
           public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
               System.out.println(path.getFileName());
               System.out.println(attrs.lastModifiedTime());
               return FileVisitResult.CONTINUE;
           }

           @Override
            public FileVisitResult visitFileFailed(Path path, IOException e) {
               return FileVisitResult.CONTINUE;
           }
        });
    }
}
