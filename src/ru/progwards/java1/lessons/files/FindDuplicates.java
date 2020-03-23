package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.*;

public class FindDuplicates {

    public static class FileInfo {
        private Path name;
        private FileTime lastModifiedTime;
        private long size;
        private byte[] content;

        public FileInfo(Path name, FileTime lastModifiedTime, long size, byte[] content) {
            this.name = name;
            this.lastModifiedTime = lastModifiedTime;
            this.size = size;
            this.content = content;
        }

        public Path getName() {
            return name;
        }

        public FileTime getLastModifiedTime() {
            return lastModifiedTime;
        }

        public long getSize() {
            return size;
        }

        public byte[] getContent() {
            return content;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FileInfo fileInfo = (FileInfo) o;
            return size == fileInfo.size &&
                    name.equals(fileInfo.name) &&
                    lastModifiedTime.equals(fileInfo.lastModifiedTime) &&
                    Arrays.equals(content, fileInfo.content);
        }
    }

    public List<List<String>> findDuplicates(String startPath) throws IOException {
        List<List<String>> list = new ArrayList<>();
        ArrayList<String> listPaths = new ArrayList<>();
        Map<FileInfo, Path> mapFiles = new LinkedHashMap<>();
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**");

        Files.walkFileTree(Paths.get(startPath), new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                try {
                    if (!attrs.isDirectory()) {
                        FileInfo fileInfo = new FileInfo(path.getFileName(), attrs.lastModifiedTime(), attrs.size(), Files.readAllBytes(path));
                        if (mapFiles.containsKey(fileInfo)) {
                            if (listPaths.isEmpty())
                                listPaths.add(mapFiles.get(fileInfo).toString());
                            listPaths.add(path.toAbsolutePath().toString());
                            list.add(listPaths);
                        }
                        mapFiles.putIfAbsent(fileInfo, path.toAbsolutePath());
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
        return list;
    }

    public static void main(String[] args) {
    }
}
