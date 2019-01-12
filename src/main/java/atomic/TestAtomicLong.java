package atomic;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Liuym
 * @date 2019/1/5 0005
 */
public class TestAtomicLong {
    public static void main(String[] args) {
        AtomicLong atomicLong = new AtomicLong(1);
        AtomicLong atomicLong1 = new AtomicLong(1);
        long l = atomicLong.incrementAndGet();
        long andIncrement = atomicLong1.getAndIncrement();
        System.out.println(l);
        System.out.println(andIncrement);
    }
}
