package com.wangheart.library.android.net.api;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.wangheart.library.android.app.LifecycleProvider;
import com.wangheart.library.android.net.result.BaseResult;
import com.wangheart.library.android.net.listener.BaseJsonRequestListener;
import com.wangheart.library.android.utils.LogUtils;
import com.wangheart.library.android.utils.RxUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * 描述说明  API基类
 * Author : eric
 * CreateDate : 16/7/28  下午4:05
 * Modified : eric
 * ModifiedDate :  16/7/28
 * Email : ericli_wang@163.com
 * Version 1.0
 */
public class BaseApi {
    protected static <RESULT extends BaseResult> void subscribeOnUiThread(
            @NonNull Observable<RESULT> observable, @Nullable LifecycleProvider lifecycleProvider,
            @Nullable final BaseJsonRequestListener<RESULT> listener) {
        if (lifecycleProvider != null) {
            observable = observable.compose(lifecycleProvider.<RESULT>bindToLifecycle());
        }
        observable.compose(RxUtils.<RESULT>uiScheduler()).subscribe(new Observer<RESULT>() {
            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                LogUtils.w("onSubscribe " + d.isDisposed() + " " + Thread.currentThread().getName());
                if (!d.isDisposed()) {
                    if (listener != null) {
                        listener.onRequestStart();
                    }
                }
            }

            @Override
            public void onNext(@io.reactivex.annotations.NonNull RESULT result) {
                LogUtils.w("onNext " + Thread.currentThread().getName());
                if (listener != null && result != null)
                    listener.onRequestSuccess(result);

            }

            @Override
            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                LogUtils.w("onError " + Thread.currentThread().getName());
                if (listener != null) {
                    listener.onRequestFailure(new Exception(e));
                    listener.onRequestComplete();
                }
            }

            @Override
            public void onComplete() {
                LogUtils.w("onComplete " + Thread.currentThread().getName());
                if (listener != null) {
                    listener.onRequestComplete();
                }
            }
        });
    }


    protected static RequestQuery generateBaseBody() {
        RequestQuery requestQuery = new RequestQuery();
        return requestQuery;
    }

    public static class RequestQuery extends DefaultMap {
        public RequestQuery add(String key, Object value) {
            super.add(key, value);
            return this;
        }

        public RequestQuery addAll(Map<String, Object> map) {
            if (map != null)
                putAll(map);
            return this;
        }
    }

    /**
     * 过滤空值
     */
    protected static class FilterEmptyMap extends HashMap<String, Object> {
        public FilterEmptyMap add(String key, Object value) {
            if (TextUtils.isEmpty(key) || value == null)
                return this;
            put(key, value);
            return this;
        }
    }

    /**
     * 默认Map
     */
    protected static class DefaultMap extends HashMap<String, Object> {
        public DefaultMap add(String key, Object value) {
            put(key, value);
            return this;
        }
    }
}
