package t1_concurrent.code;

import java.util.concurrent.ArrayBlockingQueue;

public class T9_Queue {
    //队列长度为10 add() 超出会报错full
    ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);

    {
        init();
    }

    public void init() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    while (true){
                        String data = queue.take();//阻塞获取

                        System.out.println("receive:" + data);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void add(String data) throws InterruptedException {
        queue.add(data);
        System.out.println("send:"+data);
        Thread.sleep(1000);
    }

    public static void main(String[] args) throws InterruptedException {
        T9_Queue t9_queue = new T9_Queue();
        for (int i = 0; i < 100; i++) {
            t9_queue.add(i+"");
        }


    }
}
