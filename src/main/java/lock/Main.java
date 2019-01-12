package lock;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;

/**
 * @author Liuym
 * @date 2019/1/12 0012
 */
public class Main {
    private static final NonReentrantLock lock = new NonReentrantLock();
    private static final Condition notEmpty = lock.newCondition();
    private static final Condition notFull = lock.newCondition();
    private static final Queue<String> queue = new LinkedBlockingDeque<>();
    private static final int queueSize=10;
    public static void main(String[] args) {
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    while(queue.size()==10){
                        notEmpty.await();
                    }
                    String element = "element";
                    queue.add(element);
                    System.out.println("生产了一个"+element);
                    notFull.signal();
                    System.out.println("唤醒消费者");
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    while (queue.size() ==0) {
                        notFull.await();
                    }
                    String element = queue.poll();
                    System.out.println("消费了一个"+element);
                    notEmpty.signal();
                    System.out.println("唤醒生产者");
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        });
        producer.start();
        consumer.start();
    }
}
