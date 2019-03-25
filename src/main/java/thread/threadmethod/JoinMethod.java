package thread.threadmethod;

/**
 *
 * Created by Yuming-Liu
 * 日期： 2019-03-25
 * 时间： 22:18
 */
public class JoinMethod {

    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(() -> {
            while (true) {
            }
        }, "threadOne");
        threadOne.start();
        // 使用join 会让子线程运行结束再执行后面的
        threadOne.join();

        System.out.println(Thread.currentThread().getName() + " over");
    }



}
