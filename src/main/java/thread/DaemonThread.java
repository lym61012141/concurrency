package thread;

/**
 * 守护线程和用户线程的区别
 * 最后一个非守护线程退出时，JVM会正常退出
 *
 * @author Liuym
 * @date 2019/1/5 0005
 */
public class DaemonThread {
    public static void main(String[] args) {
        Thread daemon = new Thread(new Runnable() {
            @Override
            public void run() {
                for (;;) {

                }
            }
        });
        // 设置为守护线程后，主线程执行完毕，jvm 退出
        // 若不设置为守护线程，主线程结束后，jvm 不会退出
        daemon.setDaemon(true);
        daemon.start();
        System.out.println("main thread over");
    }
}
