package t1_concurrent.code;

import java.util.concurrent.TimeUnit;

public class T3_ThreadFuWei {

    private static volatile  int i=0;
    public static void main(String[] args) throws InterruptedException {
//        m1_inrerrupt();

        m2_exeception();


    }

    private static void m2_exeception() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            public void run() {

                while (!Thread.currentThread().isInterrupted()) {//状态默认时false
                    try {
                        TimeUnit.SECONDS.sleep(1);

                        System.out.println("---------<捕获到异常后线程还在执行[因为状态被复位了]>"+i+",status="+Thread.currentThread().isInterrupted());
                        //手动给个状态让他终止
//                        Thread.currentThread().interrupt();
                        i++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.start();
        TimeUnit.SECONDS.sleep(2);
        thread.interrupt();//此处时true//被复位成false后还在一直复位操作 此处修改的true状态就看不出来了
        // 有概率会被停止:此处修改状态与异常修改状态抢占资源如果此处先执行 那么就进不了循环，还是相当于用户自己停止

        System.out.println("------------>>>>>抛出异常后会被修改:"+thread.isInterrupted());


    }

    private static void m1_inrerrupt() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    System.out.println("---------before:" + Thread.currentThread().isInterrupted());
                    Thread.interrupted();//复位操作 将标志改为false
                    System.out.println("---------after:" + Thread.currentThread().isInterrupted());
                }
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);//睡1s保证satrt先执行

        thread.interrupt();//中断标志  让用户自己断
//        thread.stop();//一定会断
//        Thread.interrupted();//重置
    }
}
