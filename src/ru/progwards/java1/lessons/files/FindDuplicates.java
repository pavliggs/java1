package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class FindDuplicates {

    public List<List<String>> findDuplicates(String startPath) {
        // здесь ключ - объект класса FileInfo, значение - список
        Map<FileInfo, List<String>> mapInfo = new LinkedHashMap<>();

        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**");

        try {
            Files.walkFileTree(Paths.get(startPath), new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    try {
                        if (pathMatcher.matches(path)) {
                            FileInfo fileInfo = new FileInfo(path.getFileName(), attrs.lastModifiedTime(), attrs.size(), Files.readAllBytes(path), path.toAbsolutePath());
                            if (mapInfo.isEmpty()) {
                                mapInfo.put(fileInfo, new ArrayList<>());
                                mapInfo.get(fileInfo).add(fileInfo.getPath().toString());
                            } else {
                                for (Map.Entry<FileInfo, List<String>> entry : mapInfo.entrySet()) {
                                    if (entry.getKey().equals(fileInfo)) {
                                        entry.getValue().add(fileInfo.getPath().toString());
                                        break;
                                    }
                                }
                                mapInfo.put(fileInfo, new ArrayList<>());
                                mapInfo.get(fileInfo).add(fileInfo.getPath().toString());
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
        return getList(mapInfo);
    }

    private List<List<String>> getList(Map<FileInfo, List<String>> mapList) {
        Set<Map.Entry<FileInfo, List<String>>> entries = mapList.entrySet();
        Iterator<Map.Entry<FileInfo, List<String>>> entryIterator = entries.iterator();

        while (entryIterator.hasNext()) {
            if (entryIterator.next().getValue().size() == 1)
                entryIterator.remove();
        }

        Collection<List<String>> listString = mapList.values();
        List<List<String>> listResult = new ArrayList<>(listString);
        return listResult;
    }

    public static void main(String[] args) {
        System.out.println(new FindDuplicates().findDuplicates("C:\\Users\\Эльдорадо\\inFolder"));
    }

}
