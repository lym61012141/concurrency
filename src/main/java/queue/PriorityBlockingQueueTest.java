package queue;

/**
 * @author Liuym
 * @date 2019/1/21 0021
 */
public class PriorityBlockingQueueTest<T> {
    /**
     * queue 插入新元素时，按照默认比较器自然排序
     * @param k
     * @param x
     * @param array
     * @param <T>
     */
    private static <T> void siftUpComparable(int k, T x, Object[] array) {
        Comparable<? super T> key = (Comparable<? super T>) x;
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = array[parent];
            if (key.compareTo((T) e) > 0) {
                break;
            }
            array[k] = e;
            k = parent;
        }
        array[k] = key;
    }

    /**
     * queue 删除队尾元素时，删除后队列按照默认比较器自然排序
     * @param k
     * @param x
     * @param array
     * @param n
     * @param <T>
     */
    private static <T> void siftDownComparable(int k, T x, Object[] array,int n) {
        if (n > 0) {
            Comparable<? super T> key = (Comparable<? super T>) x;
            int half = n >>>1;
            while (k < half) {
                int child = (k << 1) + 1;
                Object c = array[child];
                int right = child +1;
                if (right < n && ((Comparable<? super T>) c).compareTo((T) (array[right])) > 0) {
                    c = array[child = right];
                }
                if (key.compareTo((T) c) <= 0) {
                    break;
                }
                array[k]=c;
                k=child;
            }
            array[k]=key;
        }
    }
}
