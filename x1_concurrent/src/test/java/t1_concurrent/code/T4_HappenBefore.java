package t1_concurrent.code;

public class T4_HappenBefore {
    public static void main(String[] args) {
        //1 顺序规则 略
        //2 volatile规则
        //volatileRule();
        //3 传递性 略
        //4 start规则
        //startRule();
        //5 join规则
        //joinRule();
        //6 锁 监视器  锁前 Happens-before 锁后
        synchronizedRule();

    }

    private static void synchronizedRule() {




    }

    static int j = 1;

    private static void joinRule() {
//        t.join()方法只会使主线程进入等待池并等待t线程执行完毕后才会被唤醒。并不影响同一时刻处在运行状态的其他线程。
        Thread thread = new Thread(new Runnable() {
            public void run() {
                j = 100;
            }
        });

        thread.start();
        try {
            //主线程 等待此线程结束后才会结束
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(j);


    }

    static volatile int v = 1;

    private static void volatileRule() {
        //不是下面这样
        for (int i = 0; i < 30; i++) {
            v = 1;
            new Thread(new Runnable() {
                public void run() {
                    v = 2;
                }
            }).start();

            new Thread(new Runnable() {
                public void run() {
                    System.out.println(v);
                }
            }).start();


        }

    }

    private static void write() {

    }

    static int x = 1;

    private static void startRule() {


        Thread thread = new Thread(new Runnable() {
            public void run() {
                System.out.println(x);
            }
        });


        x = 10;

        thread.start();


    }
}
