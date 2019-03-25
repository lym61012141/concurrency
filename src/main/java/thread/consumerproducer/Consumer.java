package thread.consumerproducer;

/**
 * Created by Yuming-Liu
 * 日期： 2019-03-25
 * 时间： 21:03
 */
public class Consumer implements Runnable {

    private Resource r ;

    public Consumer(Resource r) {
        this.r = r;
    }

    @Override
    public void run() {
        while (true) {
            r.out();
        }
    }
}
