package lock;

import java.util.concurrent.locks.LockSupport;

/**
 * 使用park() 时注意做标志判断,以免被虚假unpark()
 * Created by Yuming-Liu
 * 日期： 2019-03-26
 * 时间： 21:58
 */
public class LockSupportTest {
    public static void main(String[] args) throws InterruptedException {

//        interruptParkThread1();
//        unparkThread2Test();
//        mainThreadParkTest();
        LockSupportTest test = new LockSupportTest();
        //使用该方法可以jstack 监控到具体的park位置
        test.parkThis();
    }

    /**
     * 使用park(this) 便于jstack 排查位置
     */
    private void parkThis() {
        LockSupport.park(this);
    }

    /**
     * main 被unpark() 获取关联许可,再次被park会直接返回,而不是阻塞
     */
    private static void mainThreadParkTest() {
        LockSupport.unpark(Thread.currentThread());
        System.out.println(Thread.currentThread().getName()+" unpark");
        LockSupport.park();
        System.out.println("end park");
    }

    /**
     * Thread 2 被park(),主线程调用unpark(t2),让t2 park()返回
     */
    private static void unparkThread2Test() {
        Thread t2 = new Thread(() -> {
            System.out.println("t2 run");
            LockSupport.park();
            System.out.println("t2 park over");
        });
        LockSupport.unpark(t2);
    }

    /**
     * 线程t1 被park(),主线程调用t1的中断方法导致t1.park() 返回
     */
    private static void interruptParkThread1() {
        Thread t1 = new Thread(() -> {
               System.out.println("t1 run");
               LockSupport.park();
               System.out.println("t1 park over");

           });
        t1.start();
        t1.interrupt();
    }
}
