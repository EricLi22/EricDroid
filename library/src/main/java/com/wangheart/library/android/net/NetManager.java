package com.wangheart.library.android.net;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.wangheart.library.android.utils.LogUtils;
import com.wangheart.library.java.utils.CollectionUtils;
import com.wangheart.library.java.utils.JsonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
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
public class NetManager {
    private static Retrofit mRetrofit;
    private static String mAddress = "";
    private static int successCode;
    private static boolean debug;

    public static class Builder {
        //请求的域名
        private String url;
        //请求超时时间
        private long readTimeout = 2 * 60;
        //连接超时时间
        private long connectTimeout = 30;
        //是否为debug模式。true则会打印日志
        private boolean debug;
        //使用自定义的gson
        private Gson gson;
        //成功的code;
        private int successCode=1;
        //拦截器
        private List<Interceptor> interceptorList;
        private List<Interceptor> networkInterceptorList;

        public Builder setSuccessCode(int successCode) {
            this.successCode = successCode;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setReadTimeout(long readTimeout) {
            this.readTimeout = readTimeout;
            return this;

        }

        public Builder setConnectTimeout(long connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;

        }

        public Builder setDebug(boolean debug) {
            this.debug = debug;
            return this;
        }

        public Builder setGson(Gson gson) {
            this.gson = gson;
            return this;
        }

        public Builder addInterceptor(Interceptor interceptor) {
            if (interceptorList == null) {
                interceptorList = new ArrayList<>();
            }
            interceptorList.add(interceptor);
            return this;
        }

        public Builder setNetworkInterceptorList(Interceptor networkInterceptor) {
            if (networkInterceptorList == null) {
                networkInterceptorList = new ArrayList<>();
            }
            networkInterceptorList.add(networkInterceptor);
            return this;
        }

        public void build() {
            NetManager.successCode=successCode;
            if (TextUtils.isEmpty(url)) {
                throw new RuntimeException("url is empty");
            }
            mAddress = url;
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.readTimeout(readTimeout, TimeUnit.SECONDS);
            builder.connectTimeout(connectTimeout, TimeUnit.SECONDS);
            if (debug) {
                //日志过滤器
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        LogUtils.v(message);
                    }
                });
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(interceptor);
            }
            if (!CollectionUtils.isEmpty(interceptorList)) {
                for (Interceptor interceptor : interceptorList) {
                    builder.addInterceptor(interceptor);
                }
            }
            builder.addNetworkInterceptor(new CommonInterceptor());
            if (!CollectionUtils.isEmpty(networkInterceptorList)) {
                for (Interceptor interceptor : networkInterceptorList) {
                    builder.addNetworkInterceptor(interceptor);
                }
            }
            OkHttpClient client = builder.build();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(mAddress)
                    .addConverterFactory(GsonConverterFactory.create(gson == null ? JsonUtil.GSON : gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();
            NetManager.debug=debug;
        }
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

    public static int getSuccessCode() {
        return successCode;
    }

    public static boolean isDebug() {
        return debug;
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
