package com.wangheart.library.android.utils;

import android.app.Application;
import android.content.Context;

/**
 * Author : eric
 * CreateDate : 2017/9/29  10:29
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */

public class AppUtils {
    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    public static Application getApplication() {
        if(mContext==null||!(mContext instanceof Application)){
            throw  new RuntimeException("context is Empty or not Application");
        }
        return (Application)mContext;
    }

    public static Context getContext() {
        if(mContext==null){
            throw  new RuntimeException("context");
        }
        return mContext;
    }
}
