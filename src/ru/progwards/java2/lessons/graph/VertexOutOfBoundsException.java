package ru.progwards.java2.lessons.graph;

public class VertexOutOfBoundsException extends RuntimeException {
    private String message;

    public VertexOutOfBoundsException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
