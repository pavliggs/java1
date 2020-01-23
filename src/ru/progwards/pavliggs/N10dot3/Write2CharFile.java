package ru.progwards.pavliggs.N10dot3;

import java.io.FileWriter;
import java.io.IOException;

public class Write2CharFile {
    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter("file1.txt");
        fileWriter.write("Я помню чудное мгновенье\n");
//        fileWriter.write("\n");
//        fileWriter.write("\n");
//        fileWriter.write("\n");
//        fileWriter.write("\n");
//        fileWriter.write("\n");
        fileWriter.write("Передо мной явилась ТЫ\n");
//        fileWriter.write("\n");
//        fileWriter.write("\n");
//        fileWriter.write("\n");
        fileWriter.write("Как мимолётное виденье\n");
        fileWriter.write("Как гений чистой красоты!");
        fileWriter.close();
    }
}
