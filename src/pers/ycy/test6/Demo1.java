package pers.ycy.test6;

public class Demo1 implements Runnable {
    int num = 50;// 因为只创建了一个Demo1对象，所以此处并不需要加上Static关键字。
    int i = 0;

    public void run() {
        while (true) {
            synchronized ("锁") {
                if (num > 0) {
                    i++;
                    System.out.println(Thread.currentThread().getName() + "卖出第" + i + "张票，还剩" + (num - 1) + "张票");
                    num--;
                } else {
                    System.out.println("票卖光了");
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Demo1 d = new Demo1();
        Thread thread1 = new Thread(d, "窗口1");
        Thread thread2 = new Thread(d, "窗口2");
        thread1.start();
        thread2.start();

    }

}