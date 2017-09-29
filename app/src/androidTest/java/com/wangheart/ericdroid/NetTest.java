package com.wangheart.ericdroid;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.wangheart.ericdroid.entity.User;
import com.wangheart.ericdroid.net.api.UserApi;
import com.wangheart.library.android.net.NetManager;
import com.wangheart.library.android.net.Result;
import com.wangheart.library.android.net.listener.BaseJsonRequestListener;
import com.wangheart.library.android.utils.LogUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class NetTest {
    @Before
    public void before(){
        LogUtils.w("===========before==========");
        new NetManager.Builder()
                .setUrl("http://192.166.11.248:9000/")
                .setDebug(true)
                .build();
    }
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.wangheart.ericdroid", appContext.getPackageName());
        UserApi.getUser(null, new BaseJsonRequestListener<Result<User>>() {
            @Override
            public void onRequestSuccess(Result<User> result) {
                super.onRequestSuccess(result);
                if(result.isSuccess()){
                    LogUtils.w("测试请求成功："+result.getData());
                }
            }
        });
    }
}
