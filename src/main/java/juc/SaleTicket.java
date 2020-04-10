package juc;

class Ticket {
    private int number = 30;    //有30张票

    public void sale() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName()
                    + "\t 卖出" + number-- + "号票\t还剩" + number);
        }
    }
}

public class SaleTicket {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                ticket.sale();
            }, String.valueOf(i)).start();
        }
    }
}
