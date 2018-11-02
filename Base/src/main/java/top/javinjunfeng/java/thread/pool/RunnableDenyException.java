package top.javinjunfeng.java.thread.pool;

/**
 * @Author: javinjunfeng
 * @Date: 2018/11/2 3:52 PM
 * @Version 1.0
 * 用于通知任务提交者，任务队列已无法再接收新的任务
 */
public class RunnableDenyException extends RuntimeException {
    public RunnableDenyException(String message){
        super(message);
    }
}
