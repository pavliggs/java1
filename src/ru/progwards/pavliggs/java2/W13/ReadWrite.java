package ru.progwards.pavliggs.java2.W13;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWrite {
    static int count = 0;

    public static void main(String[] args) {
        ReadWriteLock lock = new ReentrantReadWriteLock();

        Thread reader1 = new Thread(new Reader(lock));
        Thread reader2 = new Thread(new Reader(lock));
        Thread reader3 = new Thread(new Reader(lock));

        Thread writer1 = new Thread(new Writer(lock));
        Thread writer2 = new Thread(new Writer(lock));

        reader1.start();
        reader2.start();
        reader3.start();

        writer1.start();
        writer2.start();
    }
}

class Writer implements Runnable {
    private ReadWriteLock lock;

    public Writer(ReadWriteLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            lock.writeLock().lock();
            System.out.println("writer " + (++ReadWrite.count));
            lock.writeLock().unlock();

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Reader implements Runnable {
    private ReadWriteLock lock;

    public Reader(ReadWriteLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            lock.readLock().lock();
            System.out.println("reader " + ReadWrite.count);
            lock.readLock().unlock();

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
