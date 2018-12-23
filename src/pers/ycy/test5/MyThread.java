package pers.ycy.test5;

public class MyThread {
    public static void main(String[] args) {
        new Thread(new Test()).start();
    }

    private static class Test implements Runnable {
        @Override
        public void run() {
            
        }
    }
}
