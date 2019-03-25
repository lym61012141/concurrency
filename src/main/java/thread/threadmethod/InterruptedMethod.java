package thread.threadmethod;

import thread.base.Task;

/**
 *
 * interrupted() 会清除中断标志, isInterrupted()不会,
 * 所以设计时使用Thread.interrupted(),这样被中断的线程再次尝试重启时不会因为上次中断标志是false而终止业务
 * Thread.interrupted() 其实获取的时当前线程的中断标志
 * Created by Yuming-Liu
 * 日期： 2019-03-25
 * 时间： 22:22
 */
public class InterruptedMethod {
    public static void main(String[] args) throws InterruptedException {
        /*Thread threadOne = new Thread(() -> {
            while (!Thread.interrupted()) {

            }
            System.out.println(Thread.currentThread().getName() + "isInterrupted:"+ Thread.currentThread().isInterrupted());
        }, "threadOne");
        threadOne.start();
        threadOne.interrupt();
        threadOne.join();
        System.out.println(Thread.currentThread().getName() + " over");
        System.out.println();

        Thread threadTwo= new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {

            }
            System.out.println(Thread.currentThread().getName() + "isInterrupted:"+ Thread.currentThread().isInterrupted());
        }, "threadTwo");

        threadTwo.start();
        threadTwo.interrupt();
        threadTwo.join();
        System.out.println(Thread.currentThread().getName() + " over");
*/
        Task task = new Task();
        Thread threadThree = new Thread(task);
        threadThree.start();
        Thread.sleep(1000);
        threadThree.interrupt();
        threadThree.join();
        System.out.println(Thread.currentThread().getName() + " over");
        task.run();
    }
}
