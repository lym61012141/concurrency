package thread.threadmethod;

/**
 *
 * 当t1 调用了o.wait() ,Thread.sleep(),或者join() 在被t2 调用t1.interrupt() 时t1线程停止抛出中断异常
 * 否者在被t2 调用t1.interrupt()时只是被标记了中断,即设置isInterrupt()=true,但t1线程还继续执行
 *
 * Created by Yuming-Liu
 * 日期： 2019-03-25
 * 时间： 21:29
 */
public class MethodTest {
    private static Object o = new Object();
    private static Object b = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (o) {
                int i = 1;
                while (true) {
                    i++;
//                    System.out.println("t1 start " + i);
                    try {
                         o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
        Thread t2 = new Thread(() -> {
            synchronized (b) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t2 interrupt t1 now");
                t1.interrupt();
            }
        });
        t1.start();
        t1.join();
        t2.start();
        Thread.sleep(5000);
        System.out.println(t1.isInterrupted());
        System.exit(0);

    }
}
