package lock.reentrantlock;

import java.lang.reflect.Method;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
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
    public static void main(String[] args) throws InterruptedException, IllegalAccessException, InstantiationException {
        ReentrantTest state = new ReentrantTest();
        AbstractQueuedSynchronizer abstractQueuedSynchronizer = new AbstractQueuedSynchronizer() {
            @Override
            protected boolean tryAcquire(int arg) {
                return super.tryAcquire(arg);
            }
        };

        Class<?>[] declaredClasses = ReentrantLock.class.getDeclaredClasses();
        ReentrantLock lock = new ReentrantLock();
        Class<?> superclass = lock.getClass().getDeclaredClasses()[2].getSuperclass();
        Method[] declaredMethods = superclass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            method.setAccessible(true);
//            method.invoke()
        }

        for (Class<?> declaredClass : declaredClasses) {
            declaredClass.getDeclaredClasses();
        }


//        state.sameThreadReentrant();
//        state.diffThreadReentrant();
        /*Thread a = new Thread(() -> {
            state.sameThreadReentrant();
            System.out.println(Thread.currentThread().getName()+", state is "+ state.getState());
        });
        a.start();*/


    }


}
