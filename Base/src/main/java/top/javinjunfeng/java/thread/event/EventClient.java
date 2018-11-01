package top.javinjunfeng.java.thread.event;

import java.util.concurrent.TimeUnit;

/**
 * @Author: javinjunfeng
 * @Date: 2018/11/1 3:43 PM
 * @Version 1.0
 */
public class EventClient {
    public static void main(String[] args) {
        final EventQueue eventQueue = new EventQueue();
        new Thread(()->{
            for (;;){
                eventQueue.offer(new EventQueue.Event());
            }
        },"Producer").start();
        new Thread(()->{
            for (;;){
                eventQueue.take();
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Consumer").start();


        new Thread(()->{
            for (;;){
                eventQueue.offer(new EventQueue.Event());
            }
        },"Producer1").start();
        new Thread(()->{
            for (;;){
                eventQueue.offer(new EventQueue.Event());
            }
        },"Producer2").start();

        new Thread(()->{
            for (;;){
                eventQueue.take();
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Consumer1").start();
        new Thread(()->{
            for (;;){
                eventQueue.take();
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Consumer2").start();
    }
}
