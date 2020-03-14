package t1_concurrent.code;

import java.util.concurrent.TimeUnit;

public class T1_Thread {

    public static void main(String[] args) {
        // 线程状态模拟
        //class文件所在目录 jps 找到对应的进程号 jstatck 进程号查看状态
        //eg:
        //           java.lang.Thread.State: TIMED_WAITING (sleeping)      sleep()
        //           java.lang.Thread.State: BLOCKED (on object monitor)   锁内线程
        //           java.lang.Thread.State: WAITING (on object monitor)   wait()导致

        //1 阻塞100秒
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Time_Wating_Thread").start();
        //2 获得锁 并一直阻塞
        new Thread(new Runnable() {

            public void run() {
                while (true) {
                    synchronized (T1_Thread.class) {
                        try {
                            T1_Thread.class.wait();
                        } catch (InterruptedException i) {
                            i.printStackTrace();
                        }
                    }
                }
            }
        }, "Wating_Thread").start();

        //3 获得锁 阻塞100s
        new Thread(new BlockDemo(),"block1_").start();
        new Thread(new BlockDemo(),"block2_").start();

    }

    static class BlockDemo extends Thread {
        @Override
        public void run() {
            synchronized (BlockDemo.class) {
                while (true) {
                    try {

                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
            }
        }
    }
}
