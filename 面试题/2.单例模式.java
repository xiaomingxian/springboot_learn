//https://blog.csdn.net/lkxsnow/article/details/104258097

/**
 * 单例模式三：DCL(double checked locking)双重校验锁
 */
public class Singleton3 {
    private static Singleton3 singleton3;
    private Singleton3() {
    }
    public static Singleton3 getInstance() {
    // todo  第一次 null判断成功就不用 再进行后续的加锁操作
        if (singleton3 == null) {
            synchronized (Singleton3.class) { // 在多个线程试图在同一时间创建对象时，会通过加锁来保证只有一个线程能创建对象。
            // todo 第二次判断
                if (singleton3 == null) {
                    singleton3 = new Singleton3();
                }
            }
        }
        return singleton3;
    }
// 如果第一次检查instance不为null，那么就不需要执行下面的加锁和初始化操作。因此可以大幅降低synchronized带来的性能开销。
// 程序看起来很完美，但是这是一个不完备的优化，在线程执行到第9行代码读取到instance不为null时（第一个if），instance引用的对象有可能还没有完成初始化。
// 问题出现在创建对象的语句singleton3 = new Singleton3(); 上，在java中创建一个对象并非是一个原子操作，可以被分解成三行伪代码
    //1：分配对象的内存空间
    memory = allocate();
    //2：初始化对象
    ctorInstance(memory);
    //3：设置instance指向刚分配的内存地址
    instance = memory;
//上面三行伪代码中的2和3之间，可能会被重排序（在一些JIT编译器中）,即编译器或处理器为提高性能改变代码执行顺序，这一部分的内容稍后会详细解释，
    //重排序之后的伪代码是这样的：
    //1：分配对象的内存空间
    memory = allocate();
    //3：设置instance指向刚分配的内存地址
    instance = memory;
    //2：初始化对象
    ctorInstance(memory);


//todo    在单线程程序下，重排序不会对最终结果产生影响， 但是并发的情况下，可能会导致某些线程访问到未初始化的变量。
}





//顺序一致性理论内存模型
    //顺序一致性内存模型是一个被计算机科学家理想化了的理论参考模型，它为程序员提供了极强的内存可见性保证。顺序一致性内存模型有两大特性：
    //
    //一个线程中的所有操作必须按照程序的顺序来执行。
    //（不管程序是否同步）所有线程都只能看到一个单一的操作执行顺序。在顺序一致性内存模型中，每个操作都必须原子执行且立刻对所有线程可见。
//实际JMM模型
    //但是，顺序一致性模型只是一个理想化了的模型，在实际的JMM实现中，为了尽量提高程序运行效率，和理想的顺序一致性内存模型有以下差异：
    //
    //在顺序一致性模型中，所有操作完全按程序的顺序串行执行。在JMM中不保证单线程操作会按程序顺序执行（即指令重排序）。
    //顺序一致性模型保证所有线程只能看到一致的操作执行顺序，而JMM不保证所有线程能看到一致的操作执行顺序。
    //顺序一致性模型保证对所有的内存写操作都具有原子性，而JMM不保证对64位的long型和double型变量的读/写操作具有原子性（分为2个32位写操作进行，本文无关不细阐述）










