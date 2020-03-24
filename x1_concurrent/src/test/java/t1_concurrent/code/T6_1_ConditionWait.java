package t1_concurrent.code;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class T6_1_ConditionWait implements Runnable {

    private Lock lock;
    private Condition condition;


    public T6_1_ConditionWait(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    public void run() {
        try {
            lock.lock();
            try {
                System.out.println("=========== before 加锁");
               condition.await();
                System.out.println("=========== after 加锁");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
        }
    }
}
