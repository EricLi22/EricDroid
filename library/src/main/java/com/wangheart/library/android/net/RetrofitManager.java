package com.wangheart.library.android.net;


import android.text.TextUtils;

import com.wangheart.library.java.utils.JsonUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 描述说明  网络请求设置<br/>
 * Author : liwang <br/>
 * CreateDate : 2016/6/27 <br/>
 * Modified : liwang <br/>
 * ModifiedDate :  2016/6/27 <br/>
 * Email : ericli_wang@163.com <br/>
 * Version 1.0
 */
public class RetrofitManager {
    private static Retrofit mRetrofit;
    private static String mAddress = "";

    public static void init(String address,
                            OkHttpClient okHttpClient,
                            Converter.Factory converterFactory,
                            CallAdapter.Factory adapterFactory) {
        if (TextUtils.isEmpty(address)) {
            throw new RuntimeException("address is empty");
        }
        mRetrofit = new Retrofit.Builder()
                .baseUrl(mAddress)
                .addConverterFactory(GsonConverterFactory.create(JsonUtil.GSON))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }


    public static String getAddress() {
        return mAddress;
    }

    /**
     * 获取一个service接口
     *
     * @param service
     * @param <T>
     * @return
     */
    public static <T> T getService(final Class<T> service) {
        if (mRetrofit == null) {
            throw new RuntimeException("retrofit is null");
        }
        return mRetrofit.create(service);
    }

    /**
     * 拦截器，每次请求完后就断开
     */
    private static class CommonInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request req = chain.request();
            Request newRequest = req.newBuilder()
                    .header("Connection", "close").build();
            return chain.proceed(newRequest);
        }
    }
}
