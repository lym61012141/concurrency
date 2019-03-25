package thread.threadstart;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yuming-Liu
 * 日期： 2019-03-25
 * 时间： 19:36
 */
public class ThreadImplementsCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "ThreadImplementsCallable";
    }

    public static void main(String[] args) throws Exception {
        FutureTask<String> futureTask = new FutureTask<String>(new ThreadImplementsCallable());
//        new Thread(futureTask).start();
        String s = null;
        futureTask.run();
        s = futureTask.get();
        String s1 = futureTask.get(10L, TimeUnit.SECONDS);
        System.out.println(s);
    }
}
