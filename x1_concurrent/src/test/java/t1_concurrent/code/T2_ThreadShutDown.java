package t1_concurrent.code;

import java.util.concurrent.TimeUnit;

public class T2_ThreadShutDown {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                while (!Thread.currentThread().isInterrupted()){
                    System.out.println("-------------->>>>未中断时执行此段代码");
                }
            }
        });

        thread.start();

        TimeUnit.SECONDS.sleep(1);


        thread.interrupt();//通过修改Thread的interrupt属性 来达到效果
    }
}
