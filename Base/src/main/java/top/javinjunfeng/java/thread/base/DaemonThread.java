package top.javinjunfeng.java.thread.base;

/**
 * @Author: javinjunfeng
 * @Date: 2018/11/1 1:56 PM
 * @Version 1.0
 */
public class DaemonThread {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // 守护线程，若不设置守护线程，thread线程会一直执行；若设置守护线程，thread会虽main线程结束而结束
//        thread.setDaemon(true);
        thread.start();
        Thread.sleep(2_000L);
        System.out.println("Main thread finished lifecycle.");
    }
}
