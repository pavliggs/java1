package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class FindDuplicates {

    public List<List<String>> findDuplicates(String startPath) {
        // здесь ключ - объект класса FileInfo, значение - список
        Map<FileInfo, List<String>> mapInfo = new HashMap<>();

        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**");

        try {
            Files.walkFileTree(Paths.get(startPath), new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    try {
                        if (pathMatcher.matches(path)) {
                            // создаем объект на основании переданного пути
                            FileInfo fileInfo = new FileInfo(path.getFileName(), attrs.lastModifiedTime(), attrs.size(),
                                    Files.readAllBytes(path), path.toAbsolutePath());
                            // если mapInfo пустой
                            if (mapInfo.isEmpty()) {
                                // добавляем fileInfo в mapInfo
                                mapInfo.put(fileInfo, new ArrayList<>());
                                // в значение добавляем путь к этому файлу
                                mapInfo.get(fileInfo).add(fileInfo.getPath().toString());
                            // если mapInfo не пустой
                            } else {
                                boolean noDuplicatesFound = true;
                                // перебираем элементы mapInfo
                                for (Map.Entry<FileInfo, List<String>> entry : mapInfo.entrySet()) {
                                    // если ключ текущего элемента равен fileInfo, то в его значение добавляем путь
                                    if (entry.getKey().equals(fileInfo)) {
                                        entry.getValue().add(fileInfo.getPath().toString());
                                        // изменяем noDuplicatesFound и выходим из цикла
                                        noDuplicatesFound = false;
                                        break;
                                    }
                                }
                                // если дубликатов не найдено, то добавляем fileInfo в mapInfo
                                if (noDuplicatesFound) {
                                    mapInfo.put(fileInfo, new ArrayList<>());
                                    mapInfo.get(fileInfo).add(fileInfo.getPath().toString());
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
        return getList(mapInfo);
    }

    private List<List<String>> getList(Map<FileInfo, List<String>> mapInfo) {
        // создаем множество из элементов mapInfo
        Set<Map.Entry<FileInfo, List<String>>> entries = mapInfo.entrySet();
        // создаем итератор для множества entries, чтобы была возможность удалить элемент
        Iterator<Map.Entry<FileInfo, List<String>>> entryIterator = entries.iterator();

        // если значение элемента (список) имеет размер равный 1, то удаляем этот элемент
        while (entryIterator.hasNext()) {
            if (entryIterator.next().getValue().size() == 1)
                entryIterator.remove();
        }

        Collection<List<String>> listString = mapInfo.values();
        List<List<String>> listResult = new ArrayList<>(listString);
        return listResult;
    }

    public static void main(String[] args) {
        System.out.println(new FindDuplicates().findDuplicates("C:\\Users\\Эльдорадо\\inFolder"));
    }

}
