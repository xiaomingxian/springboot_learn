package t1_concurrent.code;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class T6_ConditionTest {
    public static void main(String[] args) throws InterruptedException {


        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        T6_1_ConditionWait t61_conditionWait = new T6_1_ConditionWait(lock, condition);
        T6_2_ConditionNotify t62_conditionNotify = new T6_2_ConditionNotify(lock, condition);

        new Thread(t61_conditionWait).start();

        new Thread(t62_conditionNotify).start();

        Thread.sleep(100);



    }
}
