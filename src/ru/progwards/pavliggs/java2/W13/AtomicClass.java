package ru.progwards.pavliggs.java2.W13;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicClass {

    static class RunnableAtomic implements Runnable {
        Counter counter;

        public RunnableAtomic(Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10_000; i++) {
                counter.increment();
            }
        }
    }

    static class Counter {
        AtomicInteger count = new AtomicInteger(0);
//        int count = 0;

        void increment() {
            count.incrementAndGet();
//            ++count;
        }
    }

    public static void main(String[] args) {
        Counter counter = new Counter();

        Thread t1 = new Thread(new RunnableAtomic(counter));
        Thread t2 = new Thread(new RunnableAtomic(counter));
        Thread t3 = new Thread(new RunnableAtomic(counter));
        Thread t4 = new Thread(new RunnableAtomic(counter));
        Thread t5 = new Thread(new RunnableAtomic(counter));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(counter.count);
    }
}

class CountDownLatchTest {
    public static void main(String[] args) {
        CountDownLatch downLatch = new CountDownLatch(10);
        int count  = (int) downLatch.getCount();

        for (int i = 0; i < count; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(downLatch.getCount());
                    downLatch.countDown();
                }
            });
            t.start();
        }

        System.out.println("await");
        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ok");
    }
}
