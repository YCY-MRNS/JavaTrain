package per.ycy.classroom;

public class SimpleThread extends Thread {

    private SimpleThread(String thread1) {
        setName(thread1);
    }

    @Override
    public void run() {
        super.run();
        int i = 0;
        while (i++ < 5) {
            try {
                System.out.println(getName() + "执行的步骤" + i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new SimpleThread("thread1").start();
        new SimpleThread("thread2").start();
    }


}
