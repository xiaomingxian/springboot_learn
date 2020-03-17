package t1_concurrent.code;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class T5_ReentrantLock {
    public static void main(String[] args) {
        //1 重入锁说明
//        chongruSuo();

        //2 ReentrantLock
//        reentrantLockDemo();
        //3 读写锁(可重入/不排他)
        rwLock();


    }

    static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    static Map map = new HashMap<String, Object>();
    static Lock read = readWriteLock.readLock();
    static Lock write = readWriteLock.writeLock();

    private static void rwLock() {
        //多个线程执行
        read();

        write();
        //写 不阻塞 不排他
        //读写 阻塞
        //写写 阻塞


    }

    private static Object write() {
        write.lock();
        try {
            return map.put("key", "value1");

        } finally {
            write.unlock();
        }


    }

    private static Object read() {
        read.lock();
        try {
            return map.get("key");
        } finally {
            read.unlock();
        }

    }

    static ReentrantLock reentrantLock = new ReentrantLock();

    private static void reentrantLockDemo() {
        reentrantLock.lock();//锁

        //需要同步的代码块

        reentrantLock.unlock();//释放

    }

    private static void chongruSuo() {
        T5_ReentrantLock t5_reentrantLock = new T5_ReentrantLock();
        t5_reentrantLock.method1();


    }


    public synchronized void method1() {

        System.out.println("----------->>>锁1");
        method2();//如果没有重入锁的概念 锁2会等锁1释放完才能获得锁  这种情况会死锁


    }

    public void method2() {
        synchronized (this) {
            System.out.println("------------->>>锁2");
        }
    }
}
