package lock;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Liuym
 * @date 2019/1/12 0012
 */
public class ThreadPoolMain {
    /**
     * 开启可重入锁，线程不会阻塞
     */
//    private static final ReentrantLock lock = new ReentrantLock();
    private static final NonReentrantLock lock = new NonReentrantLock();
    private static final Condition notEmpty = lock.newCondition();
    private static final Condition notFull = lock.newCondition();
    private static final Queue<String> queue = new LinkedBlockingDeque<>();
    private static final int queueSize = 100;
    private static final AtomicInteger producerCount = new AtomicInteger(0);
    private static final AtomicInteger consumerCount = new AtomicInteger(0);
    private static CountDownLatch countDownLatch = new CountDownLatch(200);
    private static final ThreadFactory threadName = new ThreadFactoryBuilder().setNameFormat("demo-thread-%d").build();
    private static final ExecutorService pool = new ThreadPoolExecutor(10, 100, 0, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(100), threadName,
            new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            pool.execute(()->{
                    producer();
                    consumer();
            });
            if (countDownLatch.getCount() == 0) {
                break;
            }
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.shutdownNow();
        System.out.println("pool 关闭");
        System.out.println("一共生产"+producerCount.get()+"个,"+"一共消费："+consumerCount.get()+"");
        System.out.println("pool is over");

    }

    private static void producer() {
        lock.lock();
        try {
            reentrant();
            while (queue.size() == queueSize) {
                notEmpty.await();
            }
            queue.add(Thread.currentThread().getName());
            producerCount.getAndIncrement();
            System.out.println( Thread.currentThread().getName()+"生产了："+producerCount.get()+"个");
            notFull.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();
            System.out.println("生产者 countDownLatch:"+countDownLatch.getCount());
            lock.unlock();
        }
    }

    private static void consumer() {
        lock.lock();
        try {
            while (queue.size() == 0) {
                notFull.await();
            }
            queue.poll();
            consumerCount.getAndIncrement();
            System.out.println(Thread.currentThread().getName()+"消费了："+consumerCount.get()+"个");
            notEmpty.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();
            System.out.println("消费者 countDownLatch:"+countDownLatch.getCount());
            lock.unlock();
        }
    }


    private static void reentrant() {
        try {
            lock.lock();
            System.out.println("生产者重入了");
        }finally {
            lock.unlock();
        }
    }
}
