package top.javinjunfeng.java.thread.base;

/**
 * @Author: javinjunfeng
 * @Date: 2018/11/1 10:52 AM
 * @Version 1.0
 */
public class TicketWindowRunnable implements Runnable{
    private static final int MAX = 50;
    private  int index = 1;
    private final static Object MUTEX = new Object();

    @Override
    public void run(){
        synchronized (MUTEX) {
            while (index <= MAX) {
                System.out.println("柜台：" + Thread.currentThread().getName() + " 当前的号码是：" + (index++));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        final TicketWindowRunnable ticketWindowRunnable = new TicketWindowRunnable();
        Thread ticketWindow1 = new Thread(ticketWindowRunnable,"一号柜台 ");
        Thread ticketWindow2 = new Thread(ticketWindowRunnable,"二号柜台 ");
        Thread ticketWindow3 = new Thread(ticketWindowRunnable,"三号柜台 ");
        Thread ticketWindow4 = new Thread(ticketWindowRunnable,"四号柜台 ");
        ticketWindow1.start();
        ticketWindow2.start();
        ticketWindow3.start();
        ticketWindow4.start();
    }
}
