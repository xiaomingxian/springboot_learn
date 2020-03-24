package t1_concurrent.code;

import java.util.concurrent.CountDownLatch;

public class T7_CountDownLaunch extends Thread {

    /**
     *
     */

    static CountDownLatch countDownLatch = new CountDownLatch(1);


    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new T7_CountDownLaunch().start();
        }

        //释放
        countDownLatch.countDown();

    }


    @Override
    public void run() {

        try {
            //为啥要加阻塞
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("----->>>>"+Thread.currentThread().getName());

    }

}
