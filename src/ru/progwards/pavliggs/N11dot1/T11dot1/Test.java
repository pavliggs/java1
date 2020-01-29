package ru.progwards.pavliggs.N11dot1.T11dot1;

import java.io.*;

public class Test {
    public void doSomething(int n) throws IOException {

    }

    public void test(int n) throws IOException {
        try {
            doSomething(n);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            System.out.println("finally executed");
        }
    }
}
