package lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Yuming-Liu
 * 日期： 2019-03-27
 * 时间： 20:45
 */
public class ReentrantTest {

    private ReentrantLock lock = new ReentrantLock(true);
    public void threadA(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " coming threadA");
        } catch (Exception e) {

        }finally {
//            System.out.println("unlock thread A");
//            lock.unlock();
        }
    }

    public void threadB(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " coming threadB");
        } catch (Exception e) {

        }finally {
            System.out.println("unlock thread B");
            lock.unlock();
        }
    }

    public int getState() {
        return lock.getHoldCount();

    }


    public  void getBlockThread(){
        int queueLength = lock.getQueueLength();
        boolean b = lock.hasQueuedThreads();
    }

    /**
     * 不同线程尝试获取同一把锁被阻塞
     */
    public void diffThreadReentrant() {
            Thread a = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    threadA();
                    System.out.println(getState());
                }

            },"四");

            Thread b = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    threadA();
                    System.out.println(getState());
                }
                lock.unlock();
            },"二");

        Thread c = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                threadA();
                System.out.println(getState());
            }
            lock.unlock();
        },"三");
            a.start();
            b.start();
            c.start();

    }

    /**
     * 同线程尝试获取同一把锁,可重入
     */
    public void sameThreadReentrant(){
        Thread stateThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                threadA();
                threadB();
                System.out.println(getState());
                System.out.println("sameThreadReentrant thread is "+ Thread.currentThread().getName());
            }
        });
        stateThread.start();
    }
}
