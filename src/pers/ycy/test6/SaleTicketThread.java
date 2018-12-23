package pers.ycy.test6;

public class SaleTicketThread extends Thread {
    private String name;
    private static int tickets = 10;

    public SaleTicketThread() {
    }

    private SaleTicketThread(String name) {
        this.name = name;
    }

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    private static final Object d = new Object();

    public void run() {

        while (true) {
            synchronized (d) {
                if (tickets > 0) {
                        System.out.println(name + " 正在销售第 " + tickets-- + " 票");
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        SaleTicketThread sale1 = new SaleTicketThread("窗口1");
        SaleTicketThread sale2 = new SaleTicketThread("窗口2");
        sale1.start();
        sale2.start();
    }
}
