package t1_concurrent.code;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class T6_ConditionTest {
    public static void main(String[] args) throws InterruptedException {


        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        T61_ConditionWait t61_conditionWait = new T61_ConditionWait(lock, condition);
        T62_ConditionNotify t62_conditionNotify = new T62_ConditionNotify(lock, condition);

        new Thread(t61_conditionWait).start();

        new Thread(t62_conditionNotify).start();

        Thread.sleep(100);



    }
}
