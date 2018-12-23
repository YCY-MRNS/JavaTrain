package pers.ycy.test6;


public class SaleTikcetRunnable implements Runnable {
    private int tickets = 10;

    @Override
    public void run() {
        while (tickets > 1) {
            synchronized (this) {
                    System.out.println(Thread.currentThread().getName() + " 正在销售第 " + tickets-- + " 票");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        SaleTikcetRunnable ticket = new SaleTikcetRunnable();
        Thread sale1 = new Thread(ticket, "窗口1");
        Thread sale2 = new Thread(ticket, "窗口2");
        sale1.start();
        sale2.start();
    }
}
