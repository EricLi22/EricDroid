package com.wangheart.library.android.utils;

import com.orhanobut.logger.Logger;

import java.util.Collection;
import java.util.Map;

/**
 * Author : eric
 * CreateDate : 2016/9/14  14:36
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */
public class CollectionUtils {
    public static <T> boolean isEmpty(Collection<T> list) {
        return list == null || list.size() <= 0;
    }

    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length <= 0;
    }

    public static <T> void print(Collection<T> list) {
        if (CollectionUtils.isEmpty(list))
            LogUtils.d("list is null or empty");
        for (T t : list) {
            LogUtils.d(t.toString());
        }
    }

    public static <T> void print(T[] array) {
        if (CollectionUtils.isEmpty(array))
            LogUtils.d("list is null or empty");
        for (T t : array) {
            LogUtils.d(t + "");
        }
    }

    public static <K, V> void print(Map<K, V> map) {
        if (map == null || map.isEmpty())
            Logger.w("map is null or empty");
        for (Map.Entry<K, V> entry : map.entrySet()) {
            LogUtils.d(entry.getKey() + "=" + entry.getValue());
        }
    }
}
