package top.javinjunfeng.java.thread.booleanlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * @Author: javinjunfeng
 * @Date: 2018/11/1 5:12 PM
 * @Version 1.0
 */
public class BooleanLockTest {
    // 定义 BooleanLock
    private final Lock lock = new BooleanLock();

    public void syncMethod(){
        try {
            // 加锁
            lock.lock();
            int randomInt = current().nextInt(10);
            System.out.println(currentThread().getName() + " get the lock.");
            TimeUnit.SECONDS.sleep(randomInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    public void syncMethodTimeoutable(){
        try {
            lock.lock(1000);
            int randomInt = current().nextInt(10);
            System.out.println(currentThread().getName() + " get the lock.");
            TimeUnit.SECONDS.sleep(randomInt);
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BooleanLockTest blt = new BooleanLockTest();
        // 定义一个线程并启动
        {
            IntStream.range(0, 10).mapToObj(i -> new Thread(blt::syncMethod)).forEach(Thread::start);
        }
        

        {
            new Thread(blt::syncMethod, "T1").start();
            TimeUnit.MILLISECONDS.sleep(2);
            new Thread(blt::syncMethodTimeoutable, "T2").start();
            TimeUnit.MILLISECONDS.sleep(10);
        }
    }

}
