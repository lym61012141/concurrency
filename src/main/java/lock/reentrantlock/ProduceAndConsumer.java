package lock.reentrantlock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * reentrantLock 实现生产者消费者模型
 * Created by Yuming-Liu
 * 日期： 2019-03-27
 * 时间： 20:10
 */
public class ProduceAndConsumer {
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition notEmpty = lock.newCondition();
    private static Condition notFull = lock.newCondition();
    private static ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);

    private static void proConTest() {
        ProduceAndConsumer test = new ProduceAndConsumer();
        Thread producer = test.getProducer();
        producer.start();
        Thread consumer = test.getConsumer();
        consumer.start();
    }


    private Thread getProducer() {
        return new Thread(() -> {
            lock.lock();
            try {
                while (queue.size() == 10) {
                    notEmpty.await();
                }

                System.out.println("add an element");
                queue.offer("add element");
                notFull.notify();

            } catch (Exception e) {

            } finally {
                lock.unlock();
            }

        });
    }

    private Thread getConsumer() {
        return new Thread(() -> {
            lock.lock();
            try {

                while (queue.size() == 0) {
                    notFull.await();
                }

                System.out.println(queue.poll());
                notEmpty.signal();

            } catch (Exception e) {

            } finally {
                lock.unlock();
            }
        });
    }


}
