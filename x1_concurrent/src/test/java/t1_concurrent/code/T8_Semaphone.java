package t1_concurrent.code;

import java.util.concurrent.Semaphore;

public class T8_Semaphone {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(5);


        for (int i = 0; i <100 ; i++) {
        new SemaPhoneDemo(i,semaphore).start();

        }

    }




    static class SemaPhoneDemo extends Thread {


        private int num;
        private Semaphore semaphore;


        public  SemaPhoneDemo(int num, Semaphore semaphore) {
            this.semaphore = semaphore;
            this.num = num;

        }

        @Override
        public void run(){
            try {
                semaphore.acquire();
                System.out.println("---------->>>>"+num+":获得锁");
                Thread.sleep(1000);
                System.out.println("---------->>>>"+num+":开走");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
