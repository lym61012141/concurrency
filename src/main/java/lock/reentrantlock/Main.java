package lock.reentrantlock;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试可重入锁的特性
 * 对同一个线程课重入,并且锁的state自增
 * 不同线程会被阻塞
 *
 * Created by Yuming-Liu
 * 日期： 2019-03-27
 * 时间： 20:49
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, IllegalAccessException, InstantiationException, NoSuchFieldException, InvocationTargetException {
        /**
         * 想使用sync 的方法及其父类的方法,以便于测试,例如获取AbstractQueuedSynchronizer内部Node 类型的字段
         * 使用反射的方式获取要用的字段及方法,通过反射调用
         */
        ReentrantTest state = new ReentrantTest();
        ReentrantLock lock = new ReentrantLock();
        Field syncField = lock.getClass().getDeclaredField("sync");
        syncField.setAccessible(true);
        Object o = syncField.get(lock);
        lock.lock();
        Method[] declaredMethods = o.getClass().getSuperclass().getDeclaredMethods();
        Field head = o.getClass().getSuperclass().getSuperclass().getDeclaredField("head");
        Field tail = o.getClass().getSuperclass().getSuperclass().getDeclaredField("tail");
        head.setAccessible(true);
        tail.setAccessible(true);
        Object o1 = tail.get(o);
        Object o2 = head.get(o);
        for (Method method : declaredMethods) {
            method.setAccessible(true);
            method.invoke(o);
        }
        lock.lock();


        System.out.println();

//        state.sameThreadReentrant();
//        state.diffThreadReentrant();
        /*Thread a = new Thread(() -> {
            state.sameThreadReentrant();
            System.out.println(Thread.currentThread().getName()+", state is "+ state.getState());
        });
        a.start();*/


    }


}
