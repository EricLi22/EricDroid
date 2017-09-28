package com.wangheart.library;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.wangheart.library.android.net.NetManager;
import com.wangheart.library.android.utils.LogUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Before
    public void before(){
        LogUtils.w("===========before==========");
        new NetManager.Builder()
                .setUrl("http://192.166.11.11/")
                .setDebug(true)
                .build();
    }

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.wangheart.library.test", appContext.getPackageName());
    }
}
