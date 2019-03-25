package thread;

import java.util.ArrayList;
import java.util.List;

/**
 * 死锁原因:相互持有各自的资源不释放
 * Created by Yuming-Liu
 * 日期： 2019-03-25
 * 时间： 20:06
 */
public class DeadLock {

    private static String resourceA = "a";
    private static String resourceb = "b";

    private List empty = new ArrayList();


    public static void main(String[] args) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (resourceA) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + "get resourceA");
                        synchronized (resourceb) {
                            System.out.println(Thread.currentThread().getName() + "get resourceB");
                        }
                    }
                }
            }).start();


            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (resourceb) {
                        System.out.println(Thread.currentThread().getName() + "get resourceB");
                        synchronized (resourceA) {
                            System.out.println(Thread.currentThread().getName() + "get resourceA");
                        }
                    }
                }
            }).start();

    }


}

