package ru.progwards.java1.lessons.files;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.Arrays;

public class FileInfo {
    private Path name;
    private FileTime lastModifiedTime;
    private long size;
    private byte[] content;
    private Path path;

    public FileInfo(Path name, FileTime lastModifiedTime, long size, byte[] content, Path path) {
        this.name = name;
        this.lastModifiedTime = lastModifiedTime;
        this.size = size;
        this.content = content;
        this.path = path;
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

    public Path getPath() {
        return path;
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
