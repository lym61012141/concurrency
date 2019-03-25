package thread.consumerproducer.otherdemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * jdk 文档给与的生产者消费者模型
 *
 * Created by Yuming-Liu
 * 日期： 2019-03-25
 * 时间： 21:16
 */
public class ConPro {

    final Lock lock = new ReentrantLock();//锁
    final Condition producer = lock.newCondition(); //生产
    final Condition consumer = lock.newCondition(); //消费

    final Object[] resource = new Object[100];//存储商品的容器。
    int putptr/*生产者使用的角标*/, takeptr/*消费者使用的角标*/, count/*计数器*/;

    /*生产者使用的方法，往数组中存储商品*/
    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            while (count == resource.length) //判断计数器是否已到数组长度。满了。
                producer.await();//生产就等待。

            resource[putptr] = x; //按照角标将商品存储到数组中
            System.out.println(Thread.currentThread()+" put "+x);

            if (++putptr == resource.length) //如果存储的角标到了数组的长度，就将角标归零。
                putptr = 0;
            ++count;//计数器自增。
            consumer.signal();//唤醒一个消费者
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) //如果计数器为0，说明没有商品，消费者等待。
                consumer.await();
            Object x = resource[takeptr]; //从数组中通过消费者角标获取商品。
            System.out.println(Thread.currentThread()+" take "+x);
            if (++takeptr == resource.length) //如果消费的角标等于了数组的长度，将角标归零。
                takeptr = 0;
            --count;//计数器自减。
            producer.signal();//唤醒生产者。
            return x;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConPro conPro = new ConPro();
        while (true) {
            new Thread(()->{
                try {
                    conPro.put("p");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }).start();

            new Thread(()->{
                try {
                    conPro.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
