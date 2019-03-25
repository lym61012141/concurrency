package thread.base;

/**
 * Created by Yuming-Liu
 * 日期： 2019-03-25
 * 时间： 22:34
 */
public class Task implements Runnable {
    @Override
    public void run() {
        System.out.println();
        while (/*!Thread.interrupted()*/ !Thread.currentThread().isInterrupted()) {

        }
        System.out.println(Thread.currentThread().getName() + " task isInterrupted:" + Thread.currentThread().isInterrupted());
    }
}
