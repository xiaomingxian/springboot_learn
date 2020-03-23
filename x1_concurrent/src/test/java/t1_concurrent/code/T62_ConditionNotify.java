package t1_concurrent.code;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class T62_ConditionNotify implements Runnable {


    private Lock lock;
    private Condition condition;

    public T62_ConditionNotify(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;


    }

    public void run() {
        try {
            lock.lock();

            System.out.println("-----------before 释放");
            condition.signal();
            System.out.println("-----------after 释放");
        } finally {
            lock.unlock();
        }

    }
}
