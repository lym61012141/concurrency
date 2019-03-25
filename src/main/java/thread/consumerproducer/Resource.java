package thread.consumerproducer;

/**
 * Created by Yuming-Liu
 * 日期： 2019-03-25
 * 时间： 21:01
 */
public class Resource {

    private String name;
    private int count = 0;

    //定义标记。
    private boolean flag = false;

    //1,提供设置的方法。
    public synchronized void set(String name){

        while (flag)
            try {
                this.wait();
            } catch (InterruptedException e) {
            }// t1等  t2等
        //给成员变量赋值并加上编号。
        //编号自增。
        count++;//2 3  4
        this.name = name + count;//商品1  商品2  商品3
        //打印生产了哪个商品。
        System.out.println(Thread.currentThread().getName() + "......生产者...." + this.name);//生产 商品1  生产商品2  生产商品3

        //将标记改为true。
        flag = true;
        //唤醒消费者。
        this.notifyAll();
    }

    public synchronized void out(){
        while (!flag)
            try {
                this.wait();
            } catch (InterruptedException e) {
            }//t3等  //t4等
        System.out.println(Thread.currentThread().getName() + "....消费者...." + this.name);//消费 商品1
        //将标记该为false。
        flag = false;
        //唤醒生产者。
        this.notifyAll();
    }
}
