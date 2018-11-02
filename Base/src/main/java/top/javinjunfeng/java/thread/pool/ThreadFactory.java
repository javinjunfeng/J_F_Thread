package top.javinjunfeng.java.thread.pool;

/**
 * @Author: javinjunfeng
 * @Date: 2018/11/2 3:35 PM
 * @Version 1.0
 * 创建线程的工长
 */
@FunctionalInterface
public interface ThreadFactory {
    Thread createThread(Runnable runnable);
}
