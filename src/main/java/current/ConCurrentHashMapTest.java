package current;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Yuming-Liu
 * 日期： 2019-02-26
 * 时间： 21:20
 */
public class ConCurrentHashMapTest {
    public static void main(String[] args) {
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<String, String>();
        concurrentHashMap.putIfAbsent("aa", "bb");
        Map<String, String> map = new HashMap<String, String>();
        map.putIfAbsent("aa", "bb");
    }
}
