package pers.ycy.test6;

public class ChouJiang implements Runnable {
    //所有的奖券
    private int[] arr = {10, 5, 20, 50, 100, 200, 500, 800, 2, 80, 300};
    private int num = arr.length;
    //记录对应的奖券是否抽过，为true表示已抽过、为false表示未抽过
    private boolean[] flag = new boolean[arr.length];

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                if (num > 0) {
                    //产生指定范围（与奖券下标相一致）内的随机数
                    int index = (int) (Math.random() * 11);
                    int get = arr[index];
                    // 代表这张抽奖券抽过了
                    if (!flag[index]) {
                        flag[index] = true;
                        System.out.println(Thread.currentThread().getName() + " 又产生了一个" + get + "元大奖");
                        num--;
                    }
                } else {
                    return;  //自动结束线程
                }
            }
        }
    }

    public static void main(String[] args) {
        ChouJiang c = new ChouJiang();
        Thread t1 = new Thread(c, "抽奖箱1");
        Thread t2 = new Thread(c, "抽奖箱2");
        t1.start();
        t2.start();
    }

}
