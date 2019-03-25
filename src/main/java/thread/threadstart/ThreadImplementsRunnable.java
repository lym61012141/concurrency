package thread.threadstart;

/**
 * Created by Yuming-Liu
 * 日期： 2019-03-25
 * 时间： 19:35
 */
public class ThreadImplementsRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("ThreadImplementsRunnable run");
    }

    public static void main(String[] args) {
        ThreadImplementsRunnable runnable1 = new ThreadImplementsRunnable();
        //  runnable1.run();可以执行两次以上
        runnable1.run();
        runnable1.run();
        new Thread(runnable1).start();

    }
}
