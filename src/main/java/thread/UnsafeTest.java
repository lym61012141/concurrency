package thread;

import sun.misc.Unsafe;

/**
 * 由于Unsafe 是由Bootstrap 加载的，并且在getUnsafe方法限制了必须是由Bootstrap加载，否则不允许操作，测试用例是使用AppClassLoader加载的所以，不支持
 * 如果想使用看TestUnsafe
 *
 * @author Liuym
 * @date 2019/1/5 0005
 */
public class UnsafeTest {
    static final Unsafe unsafe = Unsafe.getUnsafe();

    //记录变量state在UnsafeTest中的偏移值
    static final long stateOffset;

    private volatile long state = 0;
    static {
        try {
            stateOffset = unsafe.objectFieldOffset(UnsafeTest.class.getDeclaredField("state"));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            throw new Error(e);
        }
    }

    public static void main(String[] args) {
        UnsafeTest test = new UnsafeTest();
        Boolean success = unsafe.compareAndSwapInt(test, stateOffset, 0, 1);
        System.out.println(success);
    }
}
