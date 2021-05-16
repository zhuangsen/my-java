package com.zs.singleton;

/**
 * 双重校验锁隐患：
 * 1、此处涉及Java的指令重排优化。指令重排优化是指在不改变原语义的情况下，通过调整指令的执行顺序让程序运行地更快。
 * <p>
 * 2、JVM中没有规定编译器优化的相关内容，也即JVM可以自由地进行指令重排序的优化。
 * <p>
 * 3、此问题的关键在于由于指令重排序优化的存在，导致初始化Singleton和将对象地址赋给instance字段的顺序是不确定的。
 * <p>
 * 4、在某个线程创建单例对象时，在构造函数被调用前，就为该对象分配了内存空间并将对象的字段设置为默认值。此时就可以将分配的内存地址赋值给instance字段了，然而该对象可能还没初始化。
 * 若紧接着另一个线程来调用getInstance，获取到的就是状态不正确的对象，程序出错。
 * <p>
 * JDK5的修正：以上是双重校验锁失效的原因，不过在JDK1.5之后的版本添加了volatile关键字。
 * <p>
 * 1、volatile的一个语义是禁止指令重排序优化，也就保证了instance变量被赋值的时候对象已经是初始化过的，从而避免了上述问题。
 * <p>
 * 2、Java中的volatile变量是什么？
 * <p>
 * （1）关键字的作用有两个：
 * <p>
 * ①多线程主要围绕可见性和原子性两个特性展开，使用volatile关键字修饰的变量，保证了其在多线程之间的可见性，即每次读取到的volatile变量，一定是最新的数据。
 * <p>
 * ②代码底层执行的顺序是Java代码-->字节码-->根据字节码执行对应的C/C++代码-->C/C++代码被编译成汇编语言-->和硬件电路交互。
 * 实际中，为了获取更好的性能，JVM可能会对指令进行重排序，多线程下可能会出现一些意想不到的问题。使用volatile则会禁止语义重排序，也一定程度上降低了代码执行效率。
 * 实践角度而言，volatile的一个重要作用就是和CAS结合，保证了原子性。
 * <p>
 * （2）volatile是一个特殊的修饰符，只有成员变量才能使用它。在Java并发程序缺少同步类的情况下，多线程对成员变量的操作对其他线程是透明的。
 * volatile变量可以保证下一个读取操作会在前一个写操作之后发生。
 * <p>
 * 代码如下：
 *
 * @author madison
 * @description
 * @date 2021/5/16 11:29
 */
public class Singleton5 {
    private static volatile Singleton5 instance = null;

    private Singleton5() {
    }

    public static Singleton5 getInstance() {
        if (instance == null) {
            synchronized (Singleton5.class) {
                if (instance == null) {
                    instance = new Singleton5();
                }
            }
        }
        return instance;
    }
}
