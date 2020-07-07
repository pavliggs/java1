package ru.progwards.pavliggs.java2.L11;

public class Test {
    public static void main(String[] args) throws InterruptedException {
//        Thread thread = new Thread(new Runnable() {
//            public void run() {
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    System.out.println("thread interrupted");
//                    return;
//                }
//                System.out.println("thread started");
//            }
//        });
//        thread.start();
//        Thread.sleep(1);
//        thread.interrupt();


//        Thread thread = new Thread(new Runnable() {
//            public void run() {
//                while (true) {
//                    if(Thread.interrupted()) {
//                        System.out.println("thread interrupted");
//                        return;
//                    }
//                    System.out.println("thread started");
//                }
//            }});
//        thread.start();
//        Thread.sleep(1);
//        thread.interrupt();

        Thread thread = new Thread(new Runnable() {
            public void run() {
                while (Thread.interrupted()) {
                    System.out.println("thread interrupted");
                    return;
                }
                System.out.println("thread started");
            }
        });
        thread.start();
        Thread.sleep(1);
        thread.interrupt();
    }
}
