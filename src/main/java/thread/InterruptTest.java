package thread;

/**
 * Created by Yuming-Liu
 * 日期： 2019-02-27
 * 时间： 00:00
 */
public class InterruptTest {
    public static void main(String[] args) throws Exception{
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+" is begin");

                while (!Thread.currentThread().isInterrupted()) {
//                    System.out.println(Thread.currentThread() + "is running");
                }
                System.out.println(Thread.currentThread()+" out running");
            }
        });

        thread.start();
        Thread.sleep(10000);
        System.out.println("main thread interrupt child thread");
        thread.interrupt();
        thread.join();
        System.out.println("main thread is over");

    }
}