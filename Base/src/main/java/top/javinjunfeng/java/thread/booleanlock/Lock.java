package top.javinjunfeng.java.thread.booleanlock;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @Author: javinjunfeng
 * @Date: 2018/11/1 5:26 PM
 * @Version 1.0
 */
public interface Lock {
    void lock() throws InterruptedException;
    void lock(long mills) throws TimeoutException, InterruptedException;
    void unlock();
    List<Thread> getBlockedThreads();
}
