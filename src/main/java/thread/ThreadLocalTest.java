package thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * 定义一个threadLocal 变量，每个线程访问时都会创建自己的内存副本拷贝
 *
 * 这里练手了一个线程池创建：当线程池的任务缓存队列已满并且线程池中的线程数目达到maximumPoolSize，如果还有任务到来就会采取任务拒绝策略
 *
 * @author Liuym
 * @date 2019/1/5 0005
 */
public class ThreadLocalTest {
    public static ThreadLocal<String> localVariable = new ThreadLocal<>();
    public static void main(String[] args) {
        ThreadFactory nameThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-thread-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(2, 4, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(6),
                nameThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 10; i++) {
            pool.execute(()->{
                localVariable.set("local variable "+Thread.currentThread().getName());
                print(Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName()+" remove:"+localVariable.get());
            });
        }
        pool.shutdown();
    }
    static void print(String str) {
        System.out.println(str + ":" + localVariable.get());
        localVariable.remove();
    }
}
