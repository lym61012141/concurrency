package thread.threadmethod;

/**
 * 根据中断标志优雅退出线程
 *
 * Created by Yuming-Liu
 * 日期： 2019-03-25
 * 时间： 22:01
 */
public class InterruptMethod {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().getName()+" hello");
            }
        }, "thread");

        thread.start();
        Thread.sleep(100);
        System.out.println(Thread.currentThread().getName()+" interrupt thread");
        thread.interrupt();
        thread.join();
        System.out.println(Thread.currentThread().getName()+" over");
    }


}
