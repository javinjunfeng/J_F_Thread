package top.javinjunfeng.java.thread.base;

/**
 * @Author: javinjunfeng
 * @Date: 2018/11/1 10:19 AM
 * @Version 1.0
 */
public class TicketWindow extends Thread{
    private  String name;
    private static final int MAX = 50;
    private static int index = 1;

    public TicketWindow(String name) {
        this.name = name;
    }

    @Override
    public void run(){
        while (index <= MAX){
            System.out.println("柜台：" + name + " 当前的号码是：" + (index++));
        }
    }

    public static void main(String[] args) {
        TicketWindow ticketWindow1 = new TicketWindow("一号柜台 ");
        TicketWindow ticketWindow2 = new TicketWindow("二号柜台 ");
        TicketWindow ticketWindow3 = new TicketWindow("三号柜台 ");
        TicketWindow ticketWindow4 = new TicketWindow("四号柜台 ");
        ticketWindow1.start();
        ticketWindow2.start();
        ticketWindow3.start();
        ticketWindow4.start();
    }
}
