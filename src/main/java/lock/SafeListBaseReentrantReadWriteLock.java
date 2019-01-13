package lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 线程安全的ArrayList
 * 相对于ReentrantLock 的实现方式更为高效
 *
 * @author Liuym
 * @date 2019/1/13 0013
 */
public class SafeListBaseReentrantReadWriteLock {
    private ArrayList<String> array = new ArrayList<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public void add(String e) {
        writeLock.lock();
        try{
            array.add(e);
        }finally {
            writeLock.unlock();
        }
    }

    public void remove(String e) {
        writeLock.lock();
        try{
            array.remove(e);
        }finally {
            writeLock.unlock();
        }
    }

    public String get(int index) {
        readLock.lock();
        try{
            return array.get(index);
        }finally {
            readLock.unlock();
        }
    }

}
