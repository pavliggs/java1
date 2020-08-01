package ru.progwards.pavliggs.java2.L11;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App {
    public static void main(String[] args) throws InterruptedException {
//        Thread t1 = new MyThread();
//        t1.start();

        // поток main ждёт пока исполнится поток t1
//        t1.join();

        // прерывает поток
//        t1.interrupt();

//        Runnable runnable = new RunnableThread();
//        Thread t2 = new Thread(runnable);
//        t2.start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//            }
//        }).start();

//        System.out.println("Main exit");

        Counter counter = new Counter();

        Thread t3 = new Thread(new RunnableThread(counter));
        Thread t4 = new Thread(new RunnableThread(counter));
        Thread t5 = new Thread(new RunnableThread(counter));
        Thread t6 = new Thread(new RunnableThread(counter));
        Thread t7 = new Thread(new RunnableThread(counter));

        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();

        Thread.sleep(7000);

        System.out.println(counter.count);
    }
}

class MyThread extends Thread {

    @Override
    public void run() {
        try {
            // при вызове метода t1.interrupt() метод sleep бросает исключение и меняет interrupted на false
            sleep(3000);
        } catch (InterruptedException e) {
            interrupt();
            System.out.println(e.getMessage());
        }
        System.out.println(interrupted());
        System.out.println(Thread.currentThread().getName());
    }
}

class RunnableThread implements Runnable {
    Counter counter;

    public RunnableThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10_000; i++) {
            counter.increase();
        }
//        System.out.println(Thread.currentThread().getName());
    }
}

class Counter {
    int count;

    // в данном случаем несколько потоков ОДНОВРЕМЕННО могут обращаться к свойству count и увеличивать его на 1
    // при данном подходе мы не получим одинаковое значение count при выполнении нескольких потоков
//    public void increase() {
//        ++count;
//    }

    // synchronized гарантирует, что к этому методу в один момент времени может обратиться лишь ОДИН поток
    // то есть доступ к методу выполняется синхронно
//    public synchronized void increase() {
//        ++count;
//    }

    Object object = new Object();

//    public void increase() {
//        // синхронизация на объект object
//        synchronized (object) {
//            System.out.println(Thread.currentThread().getName());
//            ++count;
//        }
//    }
//
//    // синхронизация на объект Counter
//    public synchronized int getCount() {
//        return count;
//    }
    Lock lock = new ReentrantLock();

    public void increase() {
        lock.lock();
        try {
            ++count;
        } finally {
            lock.unlock();
        }
    }

}
