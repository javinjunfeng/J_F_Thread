package top.javinjunfeng.java.thread.hook;

import java.util.concurrent.TimeUnit;

/**
 * @Author: javinjunfeng
 * @Date: 2018/11/2 2:35 PM
 * @Version 1.0
 */
public class CaptureThreadException {
    public static void main(String[] args) {
        // 设置回掉接口
        Thread.setDefaultUncaughtExceptionHandler((t,e)->{
            System.out.println(t.getName() + " occur exception");
            e.printStackTrace();
        });

        final Thread thread = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 出现异常
            System.out.println(1 / 0);
        },"Test-Thread");

        thread.start();
    }
}
