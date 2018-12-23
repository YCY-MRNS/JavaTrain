package per.ycy.classroom;

public class SimpleRunnable {
    public static void main(String[] args) {
        new Thread(new print()).start();

    }

    private static class print implements Runnable {
        @Override
        public void run() {
            try {
                int i = 15;
                while (i-- >= 0) {
                    System.out.print("*");
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
