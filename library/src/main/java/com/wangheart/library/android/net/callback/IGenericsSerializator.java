package com.wangheart.library.android.net.callback;

/**
 * Created by JimGong on 2016/6/23.
 */
public interface IGenericsSerializator {
    <T> T transform(String response, Class<T> classOfT);
}
