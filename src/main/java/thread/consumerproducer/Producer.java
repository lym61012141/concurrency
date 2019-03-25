package thread.consumerproducer;

/**
 * Created by Yuming-Liu
 * 日期： 2019-03-25
 * 时间： 21:02
 */
public class Producer implements Runnable {

    private Resource r ;

    public Producer(Resource resource) {
        this.r = resource;
    }

    @Override
    public void run() {
        while(true) {
            r.set("面包");
        }
    }
}
