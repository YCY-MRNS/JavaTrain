package pers.ycy.test6;

public class Program2 implements Runnable {

    public static void main(String[] args) {
        new Thread(new Program2()).start();
        int i = 10;
        while (i-- >= 1) {
            try {
                Thread.sleep(200);
                System.out.print("main" + " ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        int i = 5;
        while (i-- >= 1) {
            try {
                Thread.sleep(400);
                System.out.print("new" + " ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
