package thread.consumerproducer;

/**
 * 生产者消费者模型,生产一个消费一个,通过flag控制
 * Created by Yuming-Liu
 * 日期： 2019-03-25
 * 时间： 21:04
 */
public class Main {
    public static void main(String[] args) {
        Resource resource = new Resource();
        Producer producer = new Producer(resource);
        Consumer consumer = new Consumer(resource);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
