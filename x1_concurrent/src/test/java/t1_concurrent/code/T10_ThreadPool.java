package t1_concurrent.code;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T10_ThreadPool implements  Runnable {

   static ExecutorService executorService = Executors.newFixedThreadPool(3);

    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());

    }

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            executorService.execute(new T10_ThreadPool());
        }
        executorService.shutdown();

    }
}
