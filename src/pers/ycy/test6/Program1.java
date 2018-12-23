package pers.ycy.test6;

public class Program1 {


    public static void main(String[] args) {

        new Thread_one().start();
        new Thread_two().start();

    }

    private static class Thread_one extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i <= 30; i++) {
                    if (i % 2 != 0) {
                        Thread.sleep(500);
                        System.out.print(i + " ");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Thread_two extends Thread{
        @Override
        public void run() {
            try {
                for (int i = 0; i <= 30; i++) {
                    if (i % 2 == 0) {
                        Thread.sleep(300);
                        System.out.print(i + " ");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
