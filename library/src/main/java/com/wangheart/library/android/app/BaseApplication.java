package com.wangheart.library.android.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.wangheart.library.android.utils.AppUtils;

/**
 * Author : eric
 * CreateDate : 2017/9/28  16:58
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */

public class BaseApplication extends Application {

    private static BaseApplication mInstance;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        mInstance = this;
        //解决64K问题
        MultiDex.install(this);
        AppUtils.init(this);
    }

    public static BaseApplication getInstance() {
        return mInstance;
    }
}
