package com.wangheart.library.java.utils;

import com.orhanobut.logger.Logger;
import com.wangheart.library.android.utils.LogUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Author : eric
 * CreateDate : 2016/9/14  14:36
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */
public class CollectionUtils {
    public static <T> boolean isEmpty(List<T> list) {
        return list == null || list.size() <= 0;
    }
    public static <T> boolean isEmpty(Set<T> list) {
        return list == null || list.size() <= 0;
    }

    public static <T> void print(List<T> list) {
        if (CollectionUtils.isEmpty(list))
            LogUtils.d("list is null or empty");
        for (T t : list) {
            LogUtils.d(t.toString());
        }
    }

    public static <K, V> void logMap(Map<K, V> map) {
        if (map == null || map.isEmpty())
            Logger.w("map is null or empty");
        for (Map.Entry<K, V> entry : map.entrySet()) {
            LogUtils.d(entry.getKey() + "=" + entry.getValue());
        }
    }
}
