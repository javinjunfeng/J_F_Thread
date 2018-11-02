package top.javinjunfeng.java.thread.pool;

/**
 * @Author: javinjunfeng
 * @Date: 2018/11/2 4:02 PM
 * @Version 1.0
 * 用于线程池内部，该类会使用到RunnableQueue，
 * 然后不断从queue中取出某个runnable，
 * 并运行runnable的run方法
 */
public class InternalTask implements Runnable {

    private final RunnableQueue runnableQueue;
    private volatile boolean running = true;

    public InternalTask(RunnableQueue runnableQueue){
        this.runnableQueue = runnableQueue;
    }

    @Override
    public void run() {
        // 如果当前任务为running并且没有被中断，则其将不断的从queue中获取runnable，然后执行run方法
        while (running && !Thread.currentThread().isInterrupted()){
            Runnable take = null;
            try {
                take = runnableQueue.take();
                take.run();
            } catch (InterruptedException e) {
               running = false;
               break;
            }
        }
    }

    public void stop(){
        this.running = false;
    }
}
