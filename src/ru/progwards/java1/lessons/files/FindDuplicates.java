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

    public List<List<String>> findDuplicates(String startPath) {
        Map<FileInfo, List<String>> mapFiles = new HashMap<>();

        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**");

        try {
            Files.walkFileTree(Paths.get(startPath), new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    try {
                        if (!attrs.isDirectory()) {
                            FileInfo fileInfo = new FileInfo(path.getFileName(), attrs.lastModifiedTime(), attrs.size(), Files.readAllBytes(path));
                            if (mapFiles.containsKey(fileInfo))
                                mapFiles.get(fileInfo).add(path.toAbsolutePath().toString());
                            mapFiles.putIfAbsent(fileInfo, new ArrayList<>());
                            mapFiles.get(fileInfo).add(path.toAbsolutePath().toString());
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
            Set<Map.Entry<FileInfo, List<String>>> entries = mapFiles.entrySet();

            Iterator<Map.Entry<FileInfo, List<String>>> entryIterator = entries.iterator();

            while (entryIterator.hasNext()) {
                if (entryIterator.next().getValue().size() == 1)
                    entryIterator.remove();
            }

            Collection<List<String>> list = mapFiles.values();

            List<List<String>> listResult = new ArrayList<>(list);
            return listResult;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(new FindDuplicates().findDuplicates(""));
    }
}
