package per.ycy.classroom;

public class ThreadSafeTest  {

    public static void main(String[] args) {
        Test threadSafeTest = new Test();
        Thread tD = new Thread(threadSafeTest);
        Thread tC = new Thread(threadSafeTest);
        Thread tB = new Thread(threadSafeTest);
        Thread tA = new Thread(threadSafeTest);
        tD.setDaemon(true);
        tC.setDaemon(true);
        tB.setDaemon(true);
        tA.setDaemon(true);
        tD.start();
        tC.start();
        tB.start();
        tA.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static class Test implements Runnable {
        private int num = 10;
        @Override
        public void run() {
            while (true) {
                if (num > 0) {
                    try {

                        Thread.sleep(100);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("ticket" + num--);
                }
            }
        }
    }
}
