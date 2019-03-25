package thread.threadstart;

/**
 *
 * Created by Yuming-Liu
 * 日期： 2019-03-25
 * 时间： 19:33
 */
public class TreadExtends extends Thread {
    @Override
    public void run() {
        System.out.println("thread extents run");
    }

    public static void main(String[] args) {
        TreadExtends treadExtends1 = new TreadExtends();
        treadExtends1.start();
//        treadExtends1.start(); 只能执行一次

    }
}
